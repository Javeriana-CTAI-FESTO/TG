import { Component, OnInit } from '@angular/core';
import { DashboardService } from '../dashboard.service';
import { retryWhen, delayWhen, take } from 'rxjs/operators';
import { timer } from 'rxjs';
@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  bigChart: any = [];
  pieChart: any = [];
  ganttChart: any = [];
  cardData = '';

  constructor(private dashboardService: DashboardService) { }

  ngOnInit() {
    this.bigChart = this.dashboardService.bigChartInit();
    this.dashboardService.getPieChartData().pipe(
      retryWhen(errors => errors.pipe(
        delayWhen(() => timer(10000)), 
        take(5) 
      ))
    ).subscribe(data => {
      this.pieChart = data;
    });
    
    this.dashboardService.ganttChart().then(data => {
      this.ganttChart = data;
    }).catch(error => {
      console.error('Error while fetching gantt chart data:', error);
      const retryGanttChart = () => {
        this.dashboardService.ganttChart().then(data => {
          this.ganttChart = data;
        }).catch(error => {
          console.error('Error while fetching gantt chart data:', error);
          setTimeout(retryGanttChart, 10000);
        });
      };
      setTimeout(retryGanttChart, 10000); 
    });
  }

  updateCardData(data: string) {
    this.cardData = data;
    this.dashboardService.GetOrdesBigChart(Number(this.cardData)).subscribe(data => {
      this.bigChart = data;
    }
    );
  }
}