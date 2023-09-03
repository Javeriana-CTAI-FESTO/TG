import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';
@Injectable({
  providedIn: 'root'
})
export class DashboardService {

  constructor(private http: HttpClient) { }
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
    return this.http.get<IndicatorData[]>('http://localhost:8080/api/admin/indicators')
      .pipe(
        map(data => data.map(item => ({
          name: item.indicatorName,
          y: item.indicatorValue,
          description: item.indicatorDescription
        })))
      );
  }

  async ganttChart() {
    const response = await fetch('http://localhost:8080/api/admin/orders/ends');
    const data = await response.json();
    return data.filter((date: string) => date !== null).map((date: string) => {
      const startDate = new Date(date);
      const endDate = new Date(date);
      endDate.setMonth(startDate.getMonth() + 1);
      return {
        start: Math.round(startDate.getTime() / 1000),
        end: Math.round(endDate.getTime() / 1000),
        name: 'end',
        completed: {
          amount: 1
        }
      };
    });
  }

  getStations(): Observable<Estations[]> {
    return this.http.get<Estations[]>('http://localhost:8080/api/admin/resources');
  }
  getParts(): Observable<Part[]> {
    return this.http.get<Part[]>('http://localhost:8080/api/students/parts/production');
  }
  placeNewOrder(partNumber: number, clientNumber: number, positions: number): Observable<any> {
    const url = `http://localhost:8080/api/students/parts/production/new-order?partNumber=${partNumber}&clientNumber=${clientNumber}&positions=${positions}`;
    console.log(url);
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
  state: string;
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