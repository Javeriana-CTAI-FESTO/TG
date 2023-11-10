import { Injectable, OnInit } from '@angular/core';
import { Observable, forkJoin } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { LoginService } from '../login/login.service';
import { map, switchMap } from 'rxjs/operators';
import { of, shareReplay } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { environment } from 'src/enviroments/enviroment';

@Injectable({
  providedIn: 'root'
})
export class DashboardService {
  rol() {
    const rol = this.loginService.getRole();
    if (rol === 'STUDENT') {
      return 'students';
    }
    if (rol === 'TEACHER') {
      return 'teacher';
    }
    return 'admin';
  }

  constructor(private http: HttpClient, private loginService: LoginService) { }

  getOrders(cedula: string, authToken: string): Observable<any[]> {
    const headers = new HttpHeaders({
      Authorization: `Bearer ${authToken}`
    });
    return this.http.get<any[]>(`${environment.urlBaseSecurity}user/get/order/cedula=${cedula}`, { headers }).pipe(
      switchMap((orders: any[]) => {
        if (orders.length === 0) {
          return of([]);
        }

        const imageRequests = orders.map(order =>
          this.http.get(environment.urlBaseSecurity + `admin/storage/image/get/fileName=${order.image_filePath}`, { headers, responseType: 'blob' }).pipe(
            map(blob => {
              const url = URL.createObjectURL(blob);
              return { ...order, image_filePath: url };
            }),
            catchError(error => {
              console.error('Error fetching image', error);
              return of(order);
            })
          )
        );
        return forkJoin(imageRequests);
      })
    );
  }

  GetOrdesBigChart(id: number): Observable<ChartData> {
    return this.http.get<any[]>(environment.urlBase + this.rol() + '/orders/' + id + '/status').pipe(
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
    return this.http.get<IndicatorData[]>(environment.urlBase + this.rol() + '/indicators')
      .pipe(
        map(data => data.map(item => ({
          name: item.indicatorName,
          y: item.indicatorValue,
          description: item.indicatorDescription
        })))
      );
  }

  async ganttChart() {
    const response = await fetch(environment.urlBase + this.rol() + '/orders/ends');
    const data = await response.json();
    return data.filter((entry: any) => entry !== null).flatMap((entry: any) => {
      return Object.entries(entry).map(([key, date]: [string, any]) => {
        const startDate = new Date(date);
        return [startDate.getTime(), parseInt(key)];
      });
    });
  }

  getOrdersAdmin(): Observable<any[]> {
    return this.http.get<any[]>(environment.urlBase + 'admin/orders');
  }
  getStations(): Observable<Estations[]> {
    return this.http.get<Estations[]>(environment.urlBase + this.rol() + '/resources');
  }


  private partsCache: { [key: string]: Observable<Part[]> } = {};

  clearPartsCache(): void {
    this.partsCache = {};
    this.getParts().subscribe();
  }

  getParts(): Observable<Part[]> {
    const authToken = localStorage.getItem('authToken') ?? '';
    const headers = new HttpHeaders({
      Authorization: `Bearer ${authToken}`
    });

    const url = environment.urlBase + this.rol() + '/parts/production';

    if (this.partsCache[url]) {
      return this.partsCache[url];
    }

    const partsRequest = this.http.get<Part[]>(url).pipe(
      switchMap((parts: Part[]) => {
        const imageRequests = parts.map(part => this.getImage(part, headers));
        return forkJoin(imageRequests);
      }),
      shareReplay(1)
    );

    this.partsCache[url] = partsRequest;
    return partsRequest;
  }

  getImage(part: Part, headers: HttpHeaders): Observable<Part> {
    let picturePath = this.getPicturePath(part);
    return this.http.get(environment.urlBaseSecurity + `admin/storage/image/get/fileName=${picturePath}`, { headers, responseType: 'blob' }).pipe(
      map(blob => {
        const url = URL.createObjectURL(blob);
        return { ...part, picture: url, modifiedPictureName: picturePath };
      }),
      catchError(error => {
        console.error('Error fetching image', error);
        return of({ ...part, picture: picturePath });
      })
    );
  }




  getPicturePath(part: Part): string {
    let picturePath = part.picture;
    if (picturePath.includes('\\')) {
      const pictureParts = picturePath.split('\\');
      let pictureName = pictureParts.pop();
      const folderName = pictureParts.pop();
      if (pictureName && folderName) {
        const extension = pictureName.slice(pictureName.lastIndexOf('.'));
        pictureName = pictureName.substring(0, pictureName.lastIndexOf('.'));
        picturePath = `${pictureName}_${folderName}${extension}`;
      }
    }
    return picturePath;
  }

  getCedulaByUsername(username: string, authToken: string) {
    const headers = {
      Authorization: `Bearer ${authToken}`
    };
    return this.http.get(environment.urlBaseSecurity + `user/get/cedula/username=${username}`, { headers });
  }

  saveOrder(orderData: any, authToken: string) {
    const headers = {
      Authorization: `Bearer ${authToken}`
    };
    return this.http.post(environment.urlBaseSecurity + 'user/post/save/order', orderData, { headers });
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
  modifiedPictureName?: string;

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