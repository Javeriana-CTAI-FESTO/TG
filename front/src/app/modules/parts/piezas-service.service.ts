import { Injectable } from '@angular/core';
import { Subject, Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { LoginService } from 'src/app/login/login.service';
import { switchMap, map, of, forkJoin } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class PiezasServiceService {
  urlBase = 'http://localhost:8080/api/';
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


  getPiezasPorDefecto(): Observable<{ piezas: Pieza[], picturePaths: { name: string, url: string }[] }> {
    const authToken = localStorage.getItem('authToken') ?? '';
    const headers = {
      Authorization: `Bearer ${authToken}`
    };
  
    return this.http.get<Pieza[]>(this.urlBase + this.rol() + '/parts').pipe(
      switchMap(piezas => {
        const picturePaths: { name: string, url: string }[] = [];
        const piezasWithImages = piezas.map(pieza => {
          const pictureParts = pieza.picture.split('\\');
          let pictureName = pictureParts.pop();
          const folderName = pictureParts.pop();
          if (pictureName && folderName) {
            const extension = pictureName.slice(pictureName.lastIndexOf('.'));
            pictureName = pictureName.substring(0, pictureName.lastIndexOf('.'));
            const picturePath = `${pictureName}_${folderName}${extension}`;
  
            return this.http.get(`http://localhost:8081/api/admin/storage/image/get/fileName=${picturePath}`, { headers, responseType: 'blob' }).pipe(
              map(image => {
                // Verifica si el blob de la imagen tiene tamaño antes de agregarlo a picturePaths
                if (image.size > 0) {
                  const imageUrl = URL.createObjectURL(image);
                  if (!picturePaths.some(path => path.name === picturePath)) {
                    picturePaths.push({ name: picturePath, url: imageUrl });
                  }
                  return { ...pieza, picture: imageUrl };
                } else {
                  return pieza;
                }
              })
            );
          } else {
            return of(pieza);
          }
        });
  
        return forkJoin(piezasWithImages).pipe(
          map(piezas => ({ piezas, picturePaths }))
        );
      })
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
    return this.http.post<Pieza>(this.urlBase + this.rol() + '/parts', pieza);
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
