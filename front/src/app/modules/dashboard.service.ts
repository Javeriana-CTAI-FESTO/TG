import { Injectable } from '@angular/core';

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

  cards() {
    return [71, 78, 39, 66]
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
}
