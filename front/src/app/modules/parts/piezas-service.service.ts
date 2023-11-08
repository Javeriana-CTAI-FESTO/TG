import { Injectable } from '@angular/core';
import { Subject, Observable, from, mergeMap, tap, shareReplay } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { LoginService } from 'src/app/login/login.service';
import { switchMap, map, of, forkJoin } from 'rxjs';
import { environment } from 'src/enviroments/enviroment';
@Injectable({
  providedIn: 'root'
})
export class PiezasServiceService {
  piezas: Pieza[] = [];
  piezaAgregada = new Subject<Pieza>();

  constructor(private http: HttpClient, private loginService: LoginService) { }

  rol() {
    const rol = this.loginService.getRole();

    if (rol === 'STUDENT') {
      return 'students';
    }
    if (rol === 'TEACHER') {
      return 'teacher';
    }
    return 'admin';

  }

  private cache: { [url: string]: Observable<Blob> } = {};

  getPiezasPorDefecto(): Observable<{ piezas: Pieza[], picturePaths: { name: string, url: string }[] }> {  
    const authToken = localStorage.getItem('authToken') ?? '';
    const headers = {
      Authorization: `Bearer ${authToken}`
    };
  
    return this.http.get<Pieza[]>(environment.urlBase + this.rol() + '/parts').pipe(
      switchMap(piezas => {
        const picturePaths: { name: string, url: string }[] = [];
        const piezasWithImages = piezas.map(pieza => {
          let picturePath = pieza.picture;
          if (picturePath.includes('\\')) {
            const pictureParts = picturePath.split('\\');
            let pictureName = pictureParts.pop();
            const folderName = pictureParts.pop();
            if (pictureName && folderName) {
              const extension = pictureName.slice(pictureName.lastIndexOf('.'));
              pictureName = pictureName.substring(0, pictureName.lastIndexOf('.'));
              picturePath = `${pictureName}_${folderName}${extension}`;
            }
          }
  
          if (picturePath.startsWith('blob:')) {
            return of({ ...pieza, picture: picturePath });
          } else {
            const existingPath = picturePaths.find(path => path.name === picturePath);
            if (existingPath) {
              return of({ ...pieza, picture: existingPath.url });
            }
  
            const url = environment.urlBaseSecurity+`admin/storage/image/get/fileName=${picturePath}`;
            if (!this.cache[url]) {
              this.cache[url] = this.http.get(url, { headers, responseType: 'blob' }).pipe(
                shareReplay(1)
              );
            }
  
            return this.cache[url].pipe(
              mergeMap(image => {
                if (image.size > 0) {
                  const reader = new FileReader();
                  const reader$ = from(new Promise((resolve, reject) => {
                    reader.onloadend = () => resolve(reader.result);
                    reader.onerror = reject;
                    reader.readAsDataURL(image);
                  }));
  
                  return reader$.pipe(
                    map(imageUrl => {
                      // Verificar si picturePath ya existe en picturePaths
                      if (!picturePaths.find(path => path.name === picturePath)) {
                        picturePaths.push({ name: picturePath, url: imageUrl as string });
                      }
                      return { ...pieza, picture: imageUrl as string };
                    })
                  );
                } else {
                  return of(pieza);
                }
              })
            );
          }
        });
  
        return forkJoin(piezasWithImages).pipe(
          map(piezas => ({ piezas, picturePaths }))
        );
      }),
    );
  }
  getPiezas(): Pieza[] {
    return this.piezas;
  }
  editarPieza(piezaOriginal: Pieza, piezaNueva: Pieza): void {
    const index = this.piezas.findIndex(p => p.partNumber === piezaOriginal.partNumber);
    if (index !== -1) {
      this.piezas[index] = piezaNueva;
    }
  }
  agregarPieza(pieza: Pieza): Observable<Pieza> {
    return this.http.post<Pieza>(environment.urlBase+ this.rol() + '/parts', pieza);
  }

  uploadImage(image: Blob, fileName: string): Observable<any> {
    const authToken = localStorage.getItem('authToken') ?? '';
    const headers = {
      Authorization: `Bearer ${authToken}`
    };
  
    const formData = new FormData();
    formData.append('image', image, fileName);
  
    return this.http.post(environment.urlBaseSecurity+'admin/storage/image/upload', formData, { headers, responseType: 'text' });
  }
}

export interface Pieza {
  partNumber: number;
  description: string;
  type: number;
  workPlanNumber: number;
  picture: string;
  basePallet: number;
  mrpType: number;
  safetyStock: number;
  lotSize: number;
  defaultResourceId: number;
}