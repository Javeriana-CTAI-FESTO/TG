import { Component, OnInit, Input } from '@angular/core';
import * as Highcharts from 'highcharts';
import HC_exporting from 'highcharts/modules/exporting';

@Component({
  selector: 'app-widget-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.css']
})
export class CardComponent implements OnInit {


  @Input() label: string='';

  Highcharts = Highcharts;
  chartOptions = {};

  @Input() data = [];

  constructor() { }

  ngOnInit() {
  }



  cards = [
    {
      id: 1,
      title: 'WorkPlan',
      state:'Completed',
      imageUrl: '../../../../assets/alexandre-debieve-FO7JIlwjOtU-unsplash.jpg'
    },
    {
      id: 2,
      title: 'WorkPlan',
      state:'Completed',
      imageUrl: '../../../../assets/alexandre-debieve-FO7JIlwjOtU-unsplash.jpg'
    },
    {
      id: 3,
      title: 'WorkPlan',
      state:'Going',
      imageUrl: '../../../../assets/alexandre-debieve-FO7JIlwjOtU-unsplash.jpg'
    },
    {
      id: 4,
      title: 'WorkPlan',
      state:'Incomplete',
      imageUrl: '../../../../assets/alexandre-debieve-FO7JIlwjOtU-unsplash.jpg'
    },
    {
      id: 5,
      title: 'WorkPlan',
      state:'Incomplete',
      imageUrl: '../../../../assets/alexandre-debieve-FO7JIlwjOtU-unsplash.jpg'
    },
    {
      id: 6,
      title: 'WorkPlan',
      state:'Incomplete',
      imageUrl: '../../../../assets/alexandre-debieve-FO7JIlwjOtU-unsplash.jpg'
    },
    {
      id: 7,
      title: 'WorkPlan',
      state:'Incomplete',
      imageUrl: '../../../../assets/alexandre-debieve-FO7JIlwjOtU-unsplash.jpg'
    },
    {
      id: 8,
      title: 'WorkPlan',
      state:'Incomplete',
      imageUrl: '../../../../assets/alexandre-debieve-FO7JIlwjOtU-unsplash.jpg'
    },
    {
      id: 9,
      title: 'WorkPlan',
      state:'Incomplete',
      imageUrl: '../../../../assets/alexandre-debieve-FO7JIlwjOtU-unsplash.jpg'
    },
    {
      id: 10,
      title: 'WorkPlan',
      state:'Incomplete',
      imageUrl: '../../../../assets/alexandre-debieve-FO7JIlwjOtU-unsplash.jpg'
    }
  ];
}

