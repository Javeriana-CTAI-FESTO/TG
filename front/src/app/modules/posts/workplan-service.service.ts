import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class WorkplanServiceService {
  workplans: Workplan[] = [];
  workplanAgregado = new Subject<Workplan>();
  getWorkplans(): Workplan[] {
    return this.workplans;
  }
  getWorkPlansPorDefecto(): void {
    this.workplans.push({ id: 1, name: 'WorkPlan1', description: 'Descripcion WorkPlan 1', steps: [{step: 25, nextStep: 30, firstStep: true, endStep: false, resources: '12345678', TransportTime: 0, WorkingTime: 0, energy: 0, energy2: 0, operation: 'Operación 1'},{step: 30, nextStep: 35, firstStep: false, endStep: false, resources: '87654321', TransportTime: 0, WorkingTime: 0, energy: 0, energy2: 0, operation: 'Operación 2'}, {step: 35, nextStep: 40, firstStep: false, endStep: false, resources: '11111111', TransportTime: 0, WorkingTime: 0, energy: 0, energy2: 0, operation: 'Operación 3'}, {step: 40, nextStep: 45, firstStep: false, endStep: true, resources: '22222222', TransportTime: 0, WorkingTime: 0, energy: 0, energy2: 0, operation: 'Operación 4'}]});
    this.workplans.push({ id: 2, name: 'WorkPlan2', description: 'Descripcion WorkPlan 2', steps: [{step: 30, nextStep: 35, firstStep: false, endStep: false, resources: '87654321', TransportTime: 0, WorkingTime: 0, energy: 0, energy2: 0,operation: 'No definida'}]});
    this.workplans.push({ id: 3, name: 'WorkPlan3', description: 'Descripcion WorkPlan 3', steps: [{step: 35, nextStep: 40, firstStep: false, endStep: false, resources: '45678901', TransportTime: 0, WorkingTime: 0, energy: 0, energy2: 0,operation: 'No definida'}]});
    this.workplans.push({ id: 4, name: 'WorkPlan4', description: 'Descripcion WorkPlan 4', steps: [{step: 20, nextStep: 20, firstStep: false, endStep: false, resources: '45678901', TransportTime: 0, WorkingTime: 0, energy: 0, energy2: 0,operation: 'No definida'}]});
    this.workplans.push({ id: 5, name: 'WorkPlan5', description: 'Descripcion WorkPlan 5', steps: [{step: 40, nextStep: 40, firstStep: false, endStep: true, resources: '45678901', TransportTime: 0, WorkingTime: 0, energy: 0, energy2: 0,operation: 'No definida'}]});
    this.workplans.push({ id: 6, name: 'WorkPlan6', description: 'Descripcion WorkPlan 6', steps: [{step: 15, nextStep: 20, firstStep: true, endStep: false, resources: '23456789', TransportTime: 0, WorkingTime: 0, energy: 0, energy2: 0,operation: 'No definida'}]});
    this.workplans.push({ id: 7, name: 'WorkPlan7', description: 'Descripcion WorkPlan 7', steps: [{step: 20, nextStep: 25, firstStep: false, endStep: false, resources: '34567890', TransportTime: 0, WorkingTime: 0, energy: 0, energy2: 0,operation: 'No definida'}]});
    this.workplans.push({ id: 8, name: 'WorkPlan8', description: 'Descripcion WorkPlan 8', steps: [{step: 25, nextStep: 30, firstStep: false, endStep: false, resources: '09876543', TransportTime: 0, WorkingTime: 0, energy: 0, energy2: 0,operation: 'No definida'}]});
    this.workplans.push({ id: 9, name: 'WorkPlan9', description: 'Descripcion WorkPlan 9', steps: [{step: 30, nextStep: 35, firstStep: false, endStep: false, resources: '98765432', TransportTime: 0, WorkingTime: 0, energy: 0, energy2: 0,operation: 'No definida'}]});
    this.workplans.push({ id: 10, name: 'WorkPlan10', description: 'Descripcion WorkPlan 10', steps: [{step: 35, nextStep: 40, firstStep: false, endStep: false, resources: '56789012', TransportTime: 0, WorkingTime: 0, energy: 0, energy2: 0,operation: 'No definida'}]});
    this.workplans.push({ id: 11, name: 'WorkPlan11', description: 'Descripcion WorkPlan 11', steps: [{step: 40, nextStep: 40, firstStep: false, endStep: true, resources: '34567890', TransportTime: 0, WorkingTime: 0, energy: 0, energy2: 0,operation: 'No definida'}]});
  }
  addWorkPlan(workplan: Workplan): void {
    this.workplans.push(workplan);
    this.workplanAgregado.next(workplan);
  }
  editarWorkPlan(workplanOriginal: Workplan, workplanNuevo: Workplan): void {
    const index = this.workplans.findIndex(p => p.id === workplanOriginal.id);
    if (index !== -1) {
      this.workplans[index] = workplanNuevo;
    }
  }
  eliminarWorkPlan(index: String){
    this.workplans.splice(Number(index), 1);
  }
}


export interface Workplan {
  id: number;
  name: string;
  description: string;
  steps: pasos[];
  
}

export interface pasos{
  step: number;
  nextStep: number;
  firstStep: boolean;
  endStep: boolean;
  resources: string;
  operation: string;
  TransportTime: number;
  WorkingTime: number;
  energy: number;
  energy2: number;

}
