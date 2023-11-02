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
            // Si la imagen es un blob, simplemente la usamos como está
            return of({ ...pieza, picture: picturePath });
          } else {
            // Si no, hacemos una solicitud al backend para obtener la imagen
            return this.http.get(`http://localhost:8081/api/admin/storage/image/get/fileName=${picturePath}`, { headers, responseType: 'blob' }).pipe(
              switchMap(image => {
                // Verifica si el blob de la imagen tiene tamaño antes de agregarlo a picturePaths
                if (image.size > 0) {
                  return new Promise<any>((resolve, reject) => {
                    const reader = new FileReader();
                    reader.onloadend = () => resolve(reader.result);
                    reader.onerror = reject;
                    reader.readAsDataURL(image);
                  }).then(imageUrl => {
                    if (!picturePaths.some(path => path.name === picturePath)) {
                      picturePaths.push({ name: picturePath, url: imageUrl as string });
                    }
                    return { ...pieza, picture: imageUrl };
                  });
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

  uploadImage(image: Blob, fileName: string): Observable<any> {
    const authToken = localStorage.getItem('authToken') ?? '';
    const headers = {
      Authorization: `Bearer ${authToken}`
    };
  
    const formData = new FormData();
    formData.append('image', image, fileName);
  
    return this.http.post('http://localhost:8081/api/admin/storage/image/upload', formData, { headers, responseType: 'text' });
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
