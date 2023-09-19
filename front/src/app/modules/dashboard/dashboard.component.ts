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
        delayWhen(() => timer(10000)), // delay for 10 seconds before retrying
        take(5) // retry the function 2 times in case of failure
      ))
    ).subscribe(data => {
      this.pieChart = data;
    });
    
    this.dashboardService.ganttChart().then(data => {
      this.ganttChart = data;
    }).catch(error => {
      console.error('Error while fetching gantt chart data:', error);
      // retry the function 2 times with a delay of 10 seconds in between
      const retryGanttChart = () => {
        this.dashboardService.ganttChart().then(data => {
          this.ganttChart = data;
        }).catch(error => {
          console.error('Error while fetching gantt chart data:', error);
          setTimeout(retryGanttChart, 10000); // retry after 10 seconds
        });
      };
      setTimeout(retryGanttChart, 10000); // retry after 10 seconds
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