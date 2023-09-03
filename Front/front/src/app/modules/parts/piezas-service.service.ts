import { Injectable } from '@angular/core';
import { Subject, Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class PiezasServiceService {

  piezas: Pieza[] = [];
  piezaAgregada = new Subject<Pieza>();

  constructor(private http: HttpClient) { }


  getPiezasPorDefecto(): Observable<Pieza[]>  {
    return this.http.get<Pieza[]>('http://localhost:8080/api/admin/parts');

   
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
