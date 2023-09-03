import { Component, OnInit} from '@angular/core';
import { DashboardService } from '../dashboard.service';


@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  bigChart:any = [];
  pieChart:any = [];
  ganttChart:any = [];

  constructor(private dashboardService: DashboardService) { }

  ngOnInit() {
    
    this.bigChart = this.dashboardService.bigChart();
    this.dashboardService.getPieChartData().subscribe(data => {
      this.pieChart = data;
    });
    
    this.dashboardService.ganttChart().then(data => {
      this.ganttChart = data;
    });

  }

}