import { Injectable } from '@angular/core';
import { Subject, Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { LoginService } from 'src/app/login/login.service';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class PiezasServiceService {
  urlBase = 'http://localhost:8080/api/';
  piezas: Pieza[] = [];
  piezaAgregada = new Subject<Pieza>();

  constructor(private http: HttpClient, private loginService: LoginService ) { }

  rol(){
    const rol = this.loginService.getRole();
    if (rol === 'estudiante') {
      return 'students';
    }
    if (rol === 'profesor') {
      return 'teacher';
    }
    return 'admin';

  }


  getPiezasPorDefecto(): Observable<Pieza[]>  {
    return this.http.get<Pieza[]>(this.urlBase + this.rol() + '/parts').pipe(
      map(piezas => piezas.map(pieza => {
        const picturePath = pieza.picture.replace('Pictures', '../../../assets/Pictures').replace(/\\/g, '/');
        return { ...pieza, picture: picturePath };
      }))
    );
  }
  getPiezas(): Pieza[] {
    return this.piezas;
  }
  editarPieza(piezaOriginal: Pieza,piezaNueva: Pieza): void {
    const index = this.piezas.findIndex(p => p.partNumber === piezaOriginal.partNumber);
    if (index !== -1) {
      this.piezas[index] = piezaNueva;
    }
  }
  agregarPieza(pieza: Pieza): Observable<Pieza> {
    return this.http.post<Pieza>(this.urlBase + 'admin/parts', pieza);
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