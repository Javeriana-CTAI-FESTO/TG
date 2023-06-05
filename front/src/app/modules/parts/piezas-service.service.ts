import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class PiezasServiceService {

  piezas: Pieza[] = [];
  piezaAgregada = new Subject<Pieza>();



  getPiezasPorDefecto(): void {
    this.piezas.push(new Pieza('Pieza 1', '123', 'Descripción de la pieza 1', 'https://via.placeholder.com/150', 'No definido', 'No definido', 'No definido', 0, 0,'No definido'));
    this.piezas.push(new Pieza('Pieza 2', '456', 'Descripción de la pieza 2', 'https://via.placeholder.com/150', 'No definido', 'No definido', 'No definido', 0, 0,'No definido'));
    this.piezas.push(new Pieza('Pieza 3', '789', 'Descripción de la pieza 3', 'https://via.placeholder.com/150', 'No definido', 'No definido', 'No definido', 0,0,'No definido'));
    this.piezas.push(new Pieza('Pieza 4', '101', 'Descripción de la pieza 4', 'https://via.placeholder.com/150', 'No definido', 'No definido', 'No definido', 0,0,'No definido'));
    this.piezas.push(new Pieza('Pieza 5', '112', 'Descripción de la pieza 5', 'https://via.placeholder.com/150', 'No definido', 'No definido', 'No definido', 0,0,'No definido'));
    this.piezas.push(new Pieza('Pieza 6', '131', 'Descripción de la pieza 6', 'https://via.placeholder.com/150', 'No definido', 'No definido', 'No definido', 0,0,'No definido'));
    this.piezas.push(new Pieza('Pieza 7', '415', 'Descripción de la pieza 7', 'https://via.placeholder.com/150', 'No definido', 'No definido', 'No definido', 0,0,'No definido'));
    this.piezas.push(new Pieza('Pieza 8', '161', 'Descripción de la pieza 8', 'https://via.placeholder.com/150', 'No definido',   'No definido', 'No definido', 0,0,'No definido'));
    this.piezas.push(new Pieza('Pieza 9', '718', 'Descripción de la pieza 9', 'https://via.placeholder.com/150', 'No definido', 'No definido', 'No definido', 0,0,'No definido'));
    this.piezas.push(new Pieza('Pieza 10', '191', 'Descripción de la pieza 10', 'https://via.placeholder.com/150', 'No definido', 'No definido', 'No definido', 0,0,'No definido'));
    this.piezas.push(new Pieza('Pieza 11', '011', 'Descripción de la pieza 11', 'https://via.placeholder.com/150', 'No definido', 'No definido', 'No definido', 0,0,'No definido'));
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

export class Pieza {
  nombre: string;
  ID: string;
  descripcion: string;
  imagen: string;
  type: string;
  BasePalet: string;
  MRPType: string;
  stock: number;
  size: number;
  WorkPlan: string;

  constructor(nombre: string, ID: string, descripcion: string, imagen: string, type: string, BasePalet: string, MRPType: string, stock: number, size: number, WorkPlan: string) {
    this.nombre = nombre;
    this.ID = ID;
    this.descripcion = descripcion;
    this.imagen = imagen;
    this.type = type;
    this.BasePalet = BasePalet;
    this.MRPType = MRPType;
    this.stock = stock;
    this.size = size;
    this.WorkPlan = WorkPlan;
  }
}