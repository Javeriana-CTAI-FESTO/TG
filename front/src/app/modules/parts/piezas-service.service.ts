import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class PiezasServiceService {

  piezas: Pieza[] = [];
  piezaAgregada = new Subject<Pieza>();



  getPiezasPorDefecto(): void {
    /*this.piezas.push(new Pieza('Pieza 1', '123', 'Descripción de la pieza 1', 'https://via.placeholder.com/150', 'No definido', 'No definido', 'No definido', 0, 0,'No definido'));
    this.piezas.push(new Pieza('Pieza 2', '456', 'Descripción de la pieza 2', 'https://via.placeholder.com/150', 'No definido', 'No definido', 'No definido', 0, 0,'No definido'));
    this.piezas.push(new Pieza('Pieza 3', '789', 'Descripción de la pieza 3', 'https://via.placeholder.com/150', 'No definido', 'No definido', 'No definido', 0,0,'No definido'));
    this.piezas.push(new Pieza('Pieza 4', '101', 'Descripción de la pieza 4', 'https://via.placeholder.com/150', 'No definido', 'No definido', 'No definido', 0,0,'No definido'));
    this.piezas.push(new Pieza('Pieza 5', '112', 'Descripción de la pieza 5', 'https://via.placeholder.com/150', 'No definido', 'No definido', 'No definido', 0,0,'No definido'));
    this.piezas.push(new Pieza('Pieza 6', '131', 'Descripción de la pieza 6', 'https://via.placeholder.com/150', 'No definido', 'No definido', 'No definido', 0,0,'No definido'));
    this.piezas.push(new Pieza('Pieza 7', '415', 'Descripción de la pieza 7', 'https://via.placeholder.com/150', 'No definido', 'No definido', 'No definido', 0,0,'No definido'));
    this.piezas.push(new Pieza('Pieza 8', '161', 'Descripción de la pieza 8', 'https://via.placeholder.com/150', 'No definido',   'No definido', 'No definido', 0,0,'No definido'));
    this.piezas.push(new Pieza('Pieza 9', '718', 'Descripción de la pieza 9', 'https://via.placeholder.com/150', 'No definido', 'No definido', 'No definido', 0,0,'No definido'));
    this.piezas.push(new Pieza('Pieza 10', '191', 'Descripción de la pieza 10', 'https://via.placeholder.com/150', 'No definido', 'No definido', 'No definido', 0,0,'No definido'));
    this.piezas.push(new Pieza('Pieza 11', '011', 'Descripción de la pieza 11', 'https://via.placeholder.com/150', 'No definido', 'No definido', 'No definido', 0,0,'No definido'));*/

    //add piezas to the interface
    this.piezas.push({ID: 1, nombre: 'Pieza 1', descripcion: 'Descripción de la pieza 1', imagen: 'https://via.placeholder.com/150', type: 'No definido', BasePalet: 0, MRPType: 'No definido', stock: 0, size: 'No definido', WorkPlan: 'No definido'});
    this.piezas.push({ID: 2, nombre: 'Pieza 2', descripcion: 'Descripción de la pieza 2', imagen: 'https://via.placeholder.com/150', type: 'No definido', BasePalet: 0, MRPType: 'No definido', stock: 0, size: 'No definido', WorkPlan: 'No definido'});
    this.piezas.push({ID: 3, nombre: 'Pieza 3', descripcion: 'Descripción de la pieza 3', imagen: 'https://via.placeholder.com/150', type: 'No definido', BasePalet: 0, MRPType: 'No definido', stock: 0, size: 'No definido', WorkPlan: 'No definido'});
    this.piezas.push({ID: 4, nombre: 'Pieza 4', descripcion: 'Descripción de la pieza 4', imagen: 'https://via.placeholder.com/150', type: 'No definido', BasePalet: 0, MRPType: 'No definido', stock: 0, size: 'No definido', WorkPlan: 'No definido'});
    this.piezas.push({ID: 5, nombre: 'Pieza 5', descripcion: 'Descripción de la pieza 5', imagen: 'https://via.placeholder.com/150', type: 'No definido', BasePalet: 0, MRPType: 'No definido', stock: 0, size: 'No definido', WorkPlan: 'No definido'});
    this.piezas.push({ID: 6, nombre: 'Pieza 6', descripcion: 'Descripción de la pieza 6', imagen: 'https://via.placeholder.com/150', type: 'No definido', BasePalet: 0, MRPType: 'No definido', stock: 0, size: 'No definido', WorkPlan: 'No definido'});
    this.piezas.push({ID: 7, nombre: 'Pieza 7', descripcion: 'Descripción de la pieza 7', imagen: 'https://via.placeholder.com/150', type: 'No definido', BasePalet: 0, MRPType: 'No definido', stock: 0, size: 'No definido', WorkPlan: 'No definido'});
    this.piezas.push({ID: 8, nombre: 'Pieza 8', descripcion: 'Descripción de la pieza 8', imagen: 'https://via.placeholder.com/150', type: 'No definido',   BasePalet: 0, MRPType: 'No definido', stock: 0, size: 'No definido', WorkPlan: 'No definido'});
    this.piezas.push({ID: 9, nombre: 'Pieza 9', descripcion: 'Descripción de la pieza 9', imagen: 'https://via.placeholder.com/150', type: 'No definido',   BasePalet: 0, MRPType: 'No definido', stock: 0, size: 'No definido', WorkPlan: 'No definido'});
    this.piezas.push({ID: 10, nombre: 'Pieza 10', descripcion: 'Descripción de la pieza 10', imagen: 'https://via.placeholder.com/150', type: 'No definido',   BasePalet: 0, MRPType: 'No definido', stock: 0, size: 'No definido', WorkPlan: 'No definido'});
    this.piezas.push({ID: 11, nombre: 'Pieza 11', descripcion: 'Descripción de la pieza 11', imagen: 'https://via.placeholder.com/150', type: 'No definido',   BasePalet: 0, MRPType: 'No definido', stock: 0, size: 'No definido', WorkPlan: 'No definido'});

  }

  getPiezas(): Pieza[] {
    return this.piezas;
  }
  editarPieza(piezaOriginal: Pieza,piezaNueva: Pieza): void {
    const index = this.piezas.findIndex(p => p.ID === piezaOriginal.ID);
    if (index !== -1) {
      this.piezas[index] = piezaNueva;
    }
 
  }
}

export interface Pieza {
  ID: number;
  nombre: string;
  descripcion: string;
  imagen: string;
  type: string;
  BasePalet: number;
  MRPType: string;
  stock: number;
  size: string;
  WorkPlan: string;
}
