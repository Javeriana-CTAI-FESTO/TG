import { Injectable, OnInit } from '@angular/core';
import { Observable, forkJoin } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { LoginService } from '../login/login.service';
import { map, shareReplay, switchMap } from 'rxjs/operators';
import { of } from 'rxjs';
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

  private ordersCache: { [url: string]: Observable<any> } = {};

getOrders(cedula: string, authToken: string): Observable<any[]> {
  const headers = new HttpHeaders({
    Authorization: `Bearer ${authToken}`
  });
  return this.http.get<any[]>(`${environment.urlBaseSecurity}user/get/order/cedula=${cedula}`, { headers }).pipe(
    switchMap((orders: any[]) => {
      if (!Array.isArray(orders) || orders.length === 0) {
        // Emitir un valor por defecto si no hay órdenes
        return of([]);
      }
      const imageRequests = orders.map(order => {
        const url = environment.urlBaseSecurity+`admin/storage/image/get/fileName=${order.image_filePath}`;
        if (!this.ordersCache[url]) {
          this.ordersCache[url] = this.http.get(url, { headers, responseType: 'blob' }).pipe(
            shareReplay(1),
            map(blob => {
              const imageUrl = URL.createObjectURL(blob);
              return { ...order, image_filePath: imageUrl };
            }),
            catchError(error => {
              console.error('Error fetching image', error);
              return of(order);
            })
          );
        }

        return this.ordersCache[url];
      });
      return forkJoin(imageRequests);
    }),
    catchError(error => {
      console.error('Error fetching orders', error);
      // Emitir un valor por defecto en caso de un error no manejado
      return of([]);
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


  
  private partsCache: { [url: string]: Observable<any> } = {};

getParts(): Observable<Part[]> {
  const authToken = localStorage.getItem('authToken') ?? '';
  const headers = new HttpHeaders({
    Authorization: `Bearer ${authToken}`
  });

  return this.http.get<Part[]>(environment.urlBase + this.rol() + '/parts/production').pipe(
    switchMap((parts: Part[]) => {
      const imageRequests = parts.map(part => {
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

        const url = environment.urlBaseSecurity+`admin/storage/image/get/fileName=${picturePath}`;
        if (!this.partsCache[url]) {
          this.partsCache[url] = this.http.get(url, { headers, responseType: 'blob' }).pipe(
            shareReplay(1),
            map(blob => {
              const imageUrl = URL.createObjectURL(blob);
              return { ...part, picture: imageUrl, modifiedPictureName: picturePath };
            }),
            catchError(error => {
              console.error('Error fetching image', error);
              return of({ ...part, picture: picturePath });
            })
          );
        }

        return this.partsCache[url];
      });
      return forkJoin(imageRequests);
    })
  );
}



  private cache: { [url: string]: Observable<any> } = {};

  getPartsForBuyer(): Observable<any[]> {
    const authToken = localStorage.getItem('authToken') ?? '';
    const headers = new HttpHeaders({
      Authorization: `Bearer ${authToken}`
    });
  
    return this.http.get<any[]>(environment.urlBase + this.rol() + '/parts/production').pipe(
      switchMap((parts: any[]) => {
        const imageRequests = parts.map(part => {
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
  
          const url = environment.urlBaseSecurity+`admin/storage/image/get/fileName=${picturePath}`;
          if (!this.cache[url]) {
            this.cache[url] = this.http.get(url, { headers, responseType: 'blob' }).pipe(
              shareReplay(1),
              map(blob => {
                // Creamos una URL del blob
                const imageUrl = URL.createObjectURL(blob);
                // Reemplazamos el campo de imagen en la parte original con la URL del blob
                // y agregamos el nombre modificado de la imagen
                return { ...part, picture: imageUrl, modifiedPictureName: picturePath };
              }),
              catchError(error => {
                console.error('Error fetching image', error);
                // En caso de error, devolvemos la parte original sin modificar
                return of({ ...part, picture: picturePath });
              })
            );
          }
  
          return this.cache[url];
        });
        return forkJoin(imageRequests);
      })
    );
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

postDbRoute(dbRoute: string, rutaModuloJar: string) {

  const authToken = localStorage.getItem('authToken');
  const headers = new HttpHeaders().set('Authorization', `Bearer ${authToken}`);
  const body = {
    dbRoute,
    rutaModuloJar
  };
  return this.http.post(environment.urlBaseSecurity + 'admin/dbroute', body, { headers });
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