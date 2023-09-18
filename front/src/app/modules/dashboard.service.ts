import { Injectable, OnInit } from '@angular/core';
import { Observable, of } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { LoginService } from '../login/login.service';
import { map } from 'rxjs/operators';
@Injectable({
  providedIn: 'root'
})
export class DashboardService {

  urlBase = 'http://localhost:8080/api/';
  urlBaseSecurity = 'https://localhost:8443/api/';
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

  GetOrdesBigChart(id: number): Observable<ChartData> {
    return this.http.get<any[]>(this.urlBase + this.rol() + '/orders/' + id + '/status').pipe(
      map(steps => {
        const result: ChartData = {
          categories: [],
          series: [{
            type: 'column',
            name: 'Estacion',
            borderRadius: 5,
            colorByPoint: true,
            data: [],
            showInLegend: false
          }]
        };

        steps.forEach(step => {
          result.categories.push(Number(step.step.stepNumber));

          const start = step.start ? new Date(step.start).getTime() : 0;
          const end = step.end ? new Date(step.end).getTime() : 0;
          const diff = end - start;
          const diffInMinutes = diff / 60000;

          result.series[0].data.push(diffInMinutes);
        });

        return result;
      })
    );
  }
  bigChartInit(): ChartData {
    return {
      categories: [],
      series: [{
        type: 'column',
        name: 'Estacion',
        borderRadius: 5,
        colorByPoint: true,
        data: [],
        showInLegend: false
      }]
    };
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
  getCedulaByUsername(username: string, authToken: string) {
    const headers = {
      Authorization: `Bearer ${authToken}`
    };
    return this.http.get(this.urlBaseSecurity + `user/cedula/username=${username}`, { headers });
  }

  saveOrder(orderData: any, authToken: string) {
    const headers = {
      Authorization: `Bearer ${authToken}`
    };
    return this.http.post(this.urlBaseSecurity + 'user/save/order', orderData, { headers });
  }

 postDbRoute(dbRoute: string, rutaModuloJar: string) {
  
    const authToken = localStorage.getItem('authToken');
    const headers = new HttpHeaders().set('Authorization', `Bearer ${authToken}`);
    const body = {
      dbRoute,
      rutaModuloJar
    };
    return this.http.post(this.urlBaseSecurity + 'admin/dbroute', body, { headers });
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
interface Step {
  stepNumber: number;
  start: string | null;
  end: string | null;
}

interface ChartData {
  categories: number[];
  series: {
    type: string;
    name: string;
    borderRadius: number;
    colorByPoint: boolean;
    data: number[];
    showInLegend: boolean;
  }[];
}