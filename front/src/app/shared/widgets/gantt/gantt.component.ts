import { Component, Input, OnInit } from '@angular/core';
import * as Highcharts from "highcharts/highcharts-gantt";
import HC_exporting from 'highcharts/modules/exporting';
@Component({
  selector: 'app-widget-gantt',
  templateUrl: './gantt.component.html',
  styleUrls: ['./gantt.component.css']
})
export class GanttComponent implements OnInit {
  Highcharts = Highcharts;
  chartOptions: {} = {};
  @Input() data = [];
  constructor() { }

  ngOnInit(): void {
    this.chartOptions = {
      title: {
        text: "Tiempo de ejecución de tareas"
      },
      xAxis: {
        min: Date.UTC(2014, 10, 17),
        max: Date.UTC(2014, 10, 30),
        
      },
      exporting: {
        enabled: true
      },
      credits: {
        enabled: false
      },
      series: [
        {
          type: "gantt",
          name: "Tareas",
          data: this.data
        }
      ]
    };
    HC_exporting(Highcharts);

    setTimeout(() => {
      window.dispatchEvent(
        new Event('resize')
      );
    }, 300);
  }
}
