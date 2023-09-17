import { Component, OnInit } from '@angular/core';
import { DashboardService } from '../dashboard.service';

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

  constructor(private dashboardService: DashboardService) {}

  ngOnInit() {
    this.bigChart = this.dashboardService.bigChartInit();
    this.dashboardService.getPieChartData().subscribe(data => {
      this.pieChart = data;
    });

    this.dashboardService.ganttChart().then(data => {
      this.ganttChart = data;
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