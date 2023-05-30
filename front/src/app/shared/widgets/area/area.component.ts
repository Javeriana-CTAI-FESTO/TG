import { Component, OnInit, Input } from '@angular/core';
import * as Highcharts from 'highcharts';
import HC_exporting from 'highcharts/modules/exporting';

@Component({
  selector: 'app-widget-area',
  templateUrl: './area.component.html',
  styleUrls: ['./area.component.css']
})
export class AreaComponent implements OnInit {

  chartOptions: {} = {};

  @Input() data:any = {};
  Highcharts = Highcharts;
  constructor() { }

  ngOnInit(): void {
    this.chartOptions = {
      title: {
          text: 'Rendimiento de las Estaciones de la maquina',
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
  
          categories: ['primera', 'segunda', 'tercer', 'cuarta', 'quinta', 'sexta', 'septima', 'Octava', 'Novena', 'Decima', 'Undecima', 'Duodecima', 'Decimotercera', 'Decimocuarta', 'Decimoquinta']
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
      series: this.data
  };


    HC_exporting(Highcharts);
    setTimeout(() => {
      window.dispatchEvent(
        new Event('resize')
      );
    }, 300);

  }

}
