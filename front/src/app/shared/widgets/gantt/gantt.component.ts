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
        text: "Hidtorial de ejecución de tareas"
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
      ],
      navigator: {
        enabled: true,
        liveRedraw: true,
        series: {
          type: 'gantt',
          pointPlacement: 0.5,
          pointPadding: 0.25,
          accessibility: {
            enabled: false
          }
        },
        yAxis: {
          min: 0,
          max: 3,
          reversed: true,
          categories: []
        }
      },
      scrollbar: {
        enabled: true
      },
      rangeSelector: {
        enabled: true,
        selected: 0
      }
    };
    HC_exporting(Highcharts);

    setTimeout(() => {
      window.dispatchEvent(
        new Event('resize')
      );
    }, 300);
  }
}