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
        text: "Orders Ends"
      },
      
      exporting: {
        enabled: true
      },
      credits: {
        enabled: false
      },
      
      xAxis: {
        type: 'datetime'
      },
      
      series: [
        {
          type: "scatter",
          name: "Orders",
          data,
          tooltip: {
            pointFormat: 'Fecha: {point.x:%e de %b de %Y %H:%M:%S}'
          }
        }
      ],
      
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