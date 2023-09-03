import { Component, Input, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import * as Highcharts from "highcharts/highcharts-gantt";
import HC_exporting from 'highcharts/modules/exporting';

@Component({
  selector: 'app-widget-gantt',
  templateUrl: './gantt.component.html',
  styleUrls: ['./gantt.component.css']
})
export class GanttComponent implements OnInit, OnChanges {
  Highcharts = Highcharts;
  chartOptions: {} = {};
  @Input() data = [];

  constructor() { }

  ngOnInit(): void {
    this.updateChartOptions(this.data);
  }

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['data'] && changes['data'].currentValue) {
      this.updateChartOptions(changes['data'].currentValue);
    }
  }

  updateChartOptions(data: any[]): void {
    this.chartOptions = {
      title: {
        text: "Task execution history"
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
          data
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