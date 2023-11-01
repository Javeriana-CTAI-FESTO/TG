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


  getPiezasPorDefecto(): Observable<Pieza[]> {
    const authToken = localStorage.getItem('authToken') ?? '';
    const headers = {
      Authorization: `Bearer ${authToken}`
    };
  
    return this.http.get<Pieza[]>(this.urlBase + this.rol() + '/parts').pipe(
      switchMap(piezas => {
        // Mapea cada pieza a un Observable que obtiene la imagen y luego la asigna a picture
        const piezasWithImages = piezas.map(pieza => {
          const pictureParts = pieza.picture.split('\\');
          let pictureName = pictureParts.pop();
          const folderName = pictureParts.pop();
          if (pictureName && folderName) {
            const extension = pictureName.slice(pictureName.lastIndexOf('.'));
            pictureName = pictureName.substring(0, pictureName.lastIndexOf('.'));
            const picturePath = `${pictureName}_${folderName}${extension}`;
  
            // Obtiene la imagen de la URL y luego la asigna a picture
            return this.http.get(`http://localhost:8081/api/admin/storage/image/get/fileName=${picturePath}`, { headers, responseType: 'blob' }).pipe(
              map(image => {
                // Crea una URL de objeto a partir del Blob
                const imageUrl = URL.createObjectURL(image);
                return { ...pieza, picture: imageUrl };
              })
            );
          } else {
            // Si pictureName o folderName son undefined, retorna un Observable que emite la pieza sin modificar
            return of(pieza);
          }
        });
  
        // Combina todos los Observables en un solo Observable que emite un array de piezas
        return forkJoin(piezasWithImages);
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
