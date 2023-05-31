import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
export interface Estations {
  name: string;
  position: number;
  code: number;
  state: string;
}

@Injectable({
  providedIn: 'root'
})
export class DashboardService {

  constructor() { }

  bigChart() {
    return [{
      type: 'column',
      name: 'Estacion',
      borderRadius: 5,
      colorByPoint: true,
      data: [5, 8, 4, 3, 3, 2, 10, 7,
          5, 2.8, 3.6, 2.8, 1.45, 6.9,4.5],
      showInLegend: false
  }]
  }

  pieChart() {
    return [{
      name: 'Workplan 1',
      y: 70
    }, {
      name: 'Workplan 2',
      y: 14
    }, {
      name: 'Workplan 3',
      y: 4
    }, {
      name: 'Workplan 4',
      y: 2
    }, {
      name: 'Workplan 5',
      y: 1
    }, {
      name: 'Workplan 6',
      y: 1
    }, {
      name: 'Workplan 7',
      y: 1
    }, {
      name: 'Workplan 8',
      y: 2
    }]
  }

  private ELEMENT_DATA: Estations[] = [
    { position: 1, name: 'Primera estación', code: 10079, state: 'True' },
    { position: 2, name: 'Segunda estación', code: 40026, state: 'True' },
    { position: 3, name: 'Tercera estación', code: 6941, state: 'True' },
    { position: 4, name: 'Cuarta estación', code: 90122, state: 'False' },
    { position: 5, name: 'Quinta estación', code: 10811, state: 'True' },
    { position: 6, name: 'Sexta estación', code: 120107, state: 'False' },
    { position: 7, name: 'Septima estación', code: 140067, state: 'False' },
    { position: 8, name: 'Octava estación', code: 159994, state: 'True' },
    { position: 9, name: 'Novena estación', code: 189984, state: 'True' },
    { position: 10, name: 'Decima estación', code: 201797, state: 'True' },
    { position: 11, name: 'Undecima estación', code: 229897, state: 'True' },
    { position: 12, name: 'Duodecima estación', code: 24305, state: 'False' },
    { position: 13, name: 'Decimotercera estación', code: 269815, state: 'True' },
    { position: 14, name: 'Decimocuarta estación', code: 280855, state: 'True' },
    { position: 15, name: 'Decimoquinta estación', code: 309738, state: 'False' }
  ];



  getStations(): Observable<Estations[]> {
    return of(this.ELEMENT_DATA);
  }
}
