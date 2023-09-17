import { Component, OnInit, Input, OnChanges, SimpleChanges } from '@angular/core';
import * as Highcharts from 'highcharts';
import HC_exporting from 'highcharts/modules/exporting';

@Component({
  selector: 'app-widget-area',
  templateUrl: './area.component.html',
  styleUrls: ['./area.component.css']
})
export class AreaComponent implements OnInit, OnChanges {

  chartOptions: {} = {};

  @Input() data:any = {};
  Highcharts = Highcharts;

  constructor() { }

  ngOnInit(): void {
    this.updateChartOptions(this.data);
  }

  ngOnChanges(changes: SimpleChanges): void {

    if (changes['data'] && changes['data'].currentValue) {
      this.updateChartOptions(changes['data'].currentValue);
    }
  }

  updateChartOptions(data: any): void {
    this.chartOptions = {
      title: {
          text: 'Machine Stations Performance',
          align: 'left'
      },
      colors: [
        '#4caefe',
        '#3fbdf3',
        '#35c3e8',
        '#2bc9dc',
        '#20cfe1',
        '#16d4e6',
        '#0dd9db',
        '#03dfd0',
        '#00e4c5',
        '#00e9ba',
        '#00eeaf',
        '#23e274',
        '#5ddc5d',
        '#a4d44f',
        '#e1c517',
      ],
      credits: {
        enabled: false
      },
      exporting: {
        enabled: true
      },
      tooltip: {
        pointFormatter: function (this:{y:number}): string {
          return 'Rendimiento'+': ' + this.y + ' min';
        }
      },
      xAxis: {
        title: {
          text: 'Estaciones'
        },
        categories: data.categories
      },
      yAxis: {
          title: {
              text: 'tiempo (Minutos)'
          },
          labels: {
              formatter: function (this:{value:number}): string {
                  return this.value.toFixed(2) + ' min';
              }
            }
      },
      series: data.series
    };

    HC_exporting(Highcharts);
    setTimeout(() => {
      window.dispatchEvent(
        new Event('resize')
      );
    }, 300);
  }

}