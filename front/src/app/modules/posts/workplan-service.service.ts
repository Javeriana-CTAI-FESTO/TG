import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { WorkPlan } from './posts.component';
import { LoginService } from 'src/app/login/login.service';
@Injectable({
  providedIn: 'root'
})
export class WorkplanServiceService {
  urlBase = 'http://172.21.26.53:8080/api/';
  workplans: Workplan[] = [];
  workplanAgregado = new Subject<Workplan>();
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

  constructor(private http: HttpClient, private loginService: LoginService ) { }

  getWorkPlansPorDefecto(): Observable<Workplan[]> {
   return this.http.get<Workplan[]>(this.urlBase + this.rol() + '/work-plans');
  }
  getWorkplanById(workplanId: number): Observable<Workplan> {
    const url = this.urlBase + this.rol() + '/work-plans/' + workplanId;
    return this.http.get<Workplan>(url);
  }
  getSteps(): Observable<Step[]> {
    return this.http.get<Step[]>(this.urlBase + this.rol() + '/steps');
  }

  getWorkplans(): Workplan[] {
    return this.workplans;

  }

  addWorkPlan(workPlan: WorkPlan) {
    return this.http.post(this.urlBase + this.rol() + '/work-plans', workPlan);
  }
  getWorkPlanTypes(): Observable<any> {
    return this.http.get(this.urlBase + this.rol() + '/work-plans/type');
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

