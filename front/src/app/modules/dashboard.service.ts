import { Injectable, OnInit } from '@angular/core';
import { Observable, of } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { LoginService } from '../login/login.service';
import { map } from 'rxjs/operators';
@Injectable({
  providedIn: 'root'
})
export class DashboardService {

  urlBase = 'http://localhost:8080/api/';
  rol() {
    const rol = this.loginService.getRole();
    if (rol === 'estudiante') {
      return 'students';
    }
    if (rol === 'profesor') {
      return 'teacher';
    }
    return 'admin';
  }

  constructor(private http: HttpClient, private loginService: LoginService) { }
  bigChart() {
    return [{
      type: 'column',
      name: 'Estacion',
      borderRadius: 5,
      colorByPoint: true,
      data: [5, 8, 4, 3, 3, 2, 10, 7,
        5, 2.8, 3.6, 2.8, 1.45, 6.9, 4.5],
      showInLegend: false
    }]
  }

  getPieChartData() {
    return this.http.get<IndicatorData[]>(this.urlBase + this.rol() + '/indicators')
      .pipe(
        map(data => data.map(item => ({
          name: item.indicatorName,
          y: item.indicatorValue,
          description: item.indicatorDescription
        })))
      );
  }

  async ganttChart() { 
    const response = await fetch(this.urlBase + this.rol() + '/orders/ends'); 
    const data = await response.json(); 
    return data.filter((date: string) => date !== null).map((date: string) => { 
      const startDate = new Date(date); 
      return [startDate.getTime(), 1]; 
    }); 
  }


  getStations(): Observable<Estations[]> {
    return this.http.get<Estations[]>(this.urlBase + this.rol() + '/resources');
  }
  getParts(): Observable<Part[]> {
    return this.http.get<Part[]>(this.urlBase + this.rol() + '/parts/production');
  }
  placeNewOrder(partNumber: number, clientNumber: number, positions: number): Observable<any> {
    //const url = this.urlBase + this.rol() + `/parts/production/new-order?partNumber=${partNumber}&clientNumber=${clientNumber}&positions=${positions}`;
    const url = `http://localhost:8080/api/students/parts/production/new-order?partNumber=${partNumber}&clientNumber=${clientNumber}&positions=${positions}`;
    return this.http.post(url, {});
  }
}

export interface Estations {
  id: number;
  name: string;
  description: string;
  plcType: number;
  ip: string;
  picture: string | null;
  parallelProcessing: boolean;
  automatic: boolean;
  webPage: string | null;
  openWithDefaultBrowser: boolean;
  topologyType: number;
  agv: boolean;
  resourceType: number;
}
export interface Card {
  id: number;
  idworkPlan: number;
  title: string;
  OrderNumber: string;
  imageUrl: string;
}
export interface Part {
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
interface IndicatorData {
  indicatorName: string;
  indicatorDescription: string;
  indicatorValue: number;
}