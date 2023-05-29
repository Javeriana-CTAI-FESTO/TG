import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class DashboardService {

  constructor() { }

  bigChart() {
    return [{
      name: 'Asia',
      data: [502, 635, 809, 947, 1402, 3634, 5268]
    }, {
      name: 'Africa',
      data: [106, 107, 111, 133, 221, 767, 1766]
    }, {
      name: 'Europe',
      data: [163, 203, 276, 408, 547, 729, 628]
    }, {
      name: 'America',
      data: [18, 31, 54, 156, 339, 818, 1201]
    }, {
      name: 'Oceania',
      data: [2, 2, 2, 6, 13, 30, 46]
    }]
  }

  cards() {
    return [71, 78, 39, 66]
  }

  pieChart() {
    return [{
      name: 'Chrome',
      y: 70.67,
      sliced: true,
      selected: true
    }, {
      name: 'Edge',
      y: 14.77
    }, {
      name: 'Firefox',
      y: 4.86
    }, {
      name: 'Safari',
      y: 2.63
    }, {
      name: 'Internet Explorer',
      y: 1.53
    }, {
      name: 'Opera',
      y: 1.40
    }, {
      name: 'Sogou Explorer',
      y: 0.84
    }, {
      name: 'QQ',
      y: 0.51
    }, {
      name: 'Other',
      y: 2.6
    }]
  }
}
