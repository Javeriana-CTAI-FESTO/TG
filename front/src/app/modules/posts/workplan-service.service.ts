import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { WorkPlan } from './posts.component';
@Injectable({
  providedIn: 'root'
})
export class WorkplanServiceService {
  workplans: Workplan[] = [];
  workplanAgregado = new Subject<Workplan>();

  constructor(private http: HttpClient) { }

  getWorkPlansPorDefecto(): Observable<Workplan[]> {
    return this.http.get<Workplan[]>('http://localhost:8080/api/admin/work-plans');
  }
  getWorkplanById(workplanId: number): Observable<Workplan> {
    const url = `http://localhost:8080/api/admin/work-plans/${workplanId}`;
    return this.http.get<Workplan>(url);
  }
  getSteps(): Observable<Step[]> {
    return this.http.get<Step[]>('http://localhost:8080/api/admin/steps');
  }

  getWorkplans(): Workplan[] {
    console.log(this.workplans);
    return this.workplans;

  }

  addWorkPlan(workPlan: WorkPlan) {
    return this.http.post('http://localhost:8080/api/admin/work-plans', workPlan);
  }
  getWorkPlanTypes(): Observable<any> {
    return this.http.get('http://localhost:8080/api/admin/work-plans/type');
  }

}

export interface Workplan {

  workPlanNumber: number;
  description: string;
  workPlanType: number;
  shortDescription: string;
  pictureNumber: number;
  partNumber: number;

  steps: Step[];
}

export interface Step {
  stepNumber: number;
  description: string;
  workPlan: {
    workPlanNumber: number;
    description: string;
    workPlanType: string;
    shortDescription: string;
    pictureNumber: number;
    partNumber: number;
  };
  operation: {
    operationNumber: number;
    description: string;
    type: number;
    shortDescription: string;
    queryToWrite: string;
    picture: string;
    freeText: string;
  };
  nextStepNumber: number;
  firstStep: boolean;
  nextWhenError: number;
  newPartNumber: number;
  operationNumberType: number;
  resource: number;
  transportTime: number;
  error: boolean;
  sqlToWrite: string;
  calculatedElectricEnergy: number;
  calculatedCompressedAir: number;
  calculatedWorkingTime: number;
  freeText: string;
}

