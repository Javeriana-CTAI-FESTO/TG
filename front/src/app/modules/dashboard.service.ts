import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
export interface Estations {
  name: string;
  position: number;
  code: number;
  state1: string;
  state2: string;
  state3: string;
  state4: string;
  state5: string;
  state6: string;
  state7: string;
  state8: string;
}
export interface Card {
  id: number;
  title: string;
  state: string;
  imageUrl: string;
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
        5, 2.8, 3.6, 2.8, 1.45, 6.9, 4.5],
      showInLegend: false
    }]
  }

 

  pieChart() {
    return [{
      name: 'estado 1',
      y: 70,
      // color: '#ff0000' // red
    }, {
      name: 'estado 2',
      y: 14,
      //color: '#00ff00' // green
    }, {
      name: 'estado 3',
      y: 4,
      // color: '#0000ff' // blue
    }, {
      name: 'estado 4',
      y: 2,
      // color: '#ffff00' // yellow
    }]
  }

  ganttChart() {
    return [{
      start: Date.UTC(2017, 11, 1),
      end: Date.UTC(2018, 1, 2),
      completed: {
        amount: 0.95
      },
      name: 'Prototyping'
    }, {
      start: Date.UTC(2018, 1, 2),
      end: Date.UTC(2018, 11, 5),
      completed: {
        amount: 0.5
      },
      name: 'Development'
    }, {
      start: Date.UTC(2018, 11, 8),
      end: Date.UTC(2018, 11, 9),
      completed: {
        amount: 0.15
      },
      name: 'Testing'
    }, {
      start: Date.UTC(2018, 11, 9),
      end: Date.UTC(2018, 11, 19),
      completed: {
        amount: 0.3,
        fill: '#fa0'
      },
      name: 'Development'
    }, {
      start: Date.UTC(2018, 11, 10),
      end: Date.UTC(2018, 11, 23),
      name: 'Testing'
    }, {
      start: Date.UTC(2018, 11, 25, 8),
      end: Date.UTC(2018, 11, 25, 16),
      name: 'Release'
    }]
  }

  private ELEMENT_DATA: Estations[] = [
    { position: 1, name: 'Primera estación', code: 10079, state1: 'True', state2: 'False', state3: 'True', state4: 'False', state5: 'True', state6: 'False', state7: 'True', state8: 'False' },
    { position: 2, name: 'Segunda estación', code: 40026, state1: 'True', state2: 'False', state3: 'True', state4: 'False', state5: 'True', state6: 'False', state7: 'True', state8: 'False' },
    { position: 3, name: 'Tercera estación', code: 6941, state1: 'True', state2: 'True', state3: 'True', state4: 'True', state5: 'True', state6: 'True', state7: 'False', state8: 'True' },
    { position: 4, name: 'Cuarta estación', code: 90122, state1: 'False', state2: 'False', state3: 'False', state4: 'False', state5: 'False', state6: 'False', state7: 'False', state8: 'False' },
    { position: 5, name: 'Quinta estación', code: 10811, state1: 'True',  state2: 'True', state3: 'True', state4: 'True', state5: 'True', state6: 'True', state7: 'True', state8: 'True' },
    { position: 6, name: 'Sexta estación', code: 120107, state1: 'False', state2: 'False', state3: 'False', state4: 'False', state5: 'False', state6: 'False', state7: 'False', state8: 'False' },
    { position: 7, name: 'Septima estación', code: 140067, state1: 'False', state2: 'False', state3: 'False', state4: 'False', state5: 'False', state6: 'False', state7: 'False', state8: 'False' },
    { position: 8, name: 'Octava estación', code: 159994, state1: 'True', state2: 'True', state3: 'True', state4: 'True', state5: 'True', state6: 'True', state7: 'True', state8: 'True' },
    { position: 9, name: 'Novena estación', code: 189984, state1: 'True', state2: 'True', state3: 'True', state4: 'True', state5: 'True', state6: 'True', state7: 'True', state8: 'True' },
    { position: 10, name: 'Decima estación', code: 201797, state1: 'True', state2: 'True', state3: 'True', state4: 'True', state5: 'True', state6: 'True', state7: 'True', state8: 'True' },
    { position: 11, name: 'Undecima estación', code: 229897, state1: 'True', state2: 'True', state3: 'True', state4: 'True', state5: 'True', state6: 'True', state7: 'True', state8: 'True' },
    { position: 12, name: 'Duodecima estación', code: 24305, state1: 'False', state2: 'False', state3: 'False', state4: 'False', state5: 'False', state6: 'False', state7: 'False', state8: 'False' },
    { position: 13, name: 'Decimotercera estación', code: 269815, state1: 'True', state2: 'True', state3: 'True', state4: 'True', state5: 'True', state6: 'True', state7: 'True', state8: 'True' },
    { position: 14, name: 'Decimocuarta estación', code: 280855, state1: 'True', state2: 'True', state3: 'True', state4: 'True', state5: 'True', state6: 'True', state7: 'True', state8: 'True' },
    { position: 15, name: 'Decimoquinta estación', code: 309738, state1: 'False', state2: 'False', state3: 'False', state4: 'False', state5: 'False', state6: 'False', state7: 'False', state8: 'False' },
  ];



  getStations(): Observable<Estations[]> {
    return of(this.ELEMENT_DATA);
  }
}
