import { Component, OnInit } from '@angular/core';
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { ReportsServiceService } from 'src/app/modules/reports/reports-service.service';
import { PiezasServiceService } from 'src/app/modules/parts/piezas-service.service';
import { DashboardService } from 'src/app/modules/dashboard.service';
import { LoginService } from 'src/app/login/login.service';

@Component({
  selector: 'app-default',
  templateUrl: './default.component.html',
  styleUrls: ['./default.component.css']
})
export class DefaultComponent implements OnInit {

  sideBarOpen = true;
  isSmallScreen = false;

  constructor(private breakpointObserver: BreakpointObserver,
    private reportsService: ReportsServiceService, 
    private piezasService: PiezasServiceService,
    private dashboardService: DashboardService) { }

  ngOnInit(): void {
    this.breakpointObserver.observe([
      Breakpoints.Handset
    ]).subscribe(result => {
      this.isSmallScreen = result.matches;
    });
    this.reportsService.getReports().subscribe();
    this.reportsService.getReportsFails().subscribe();
    this.piezasService.getPiezasPorDefecto().subscribe();

   
    this.dashboardService.getParts().subscribe();    
  }

  sideBarToggler(event: any) {
    this.sideBarOpen = !this.sideBarOpen;
  }

  onRouteSelected() {
    if (this.isSmallScreen) {
      this.sideBarOpen = false;
    }
  }
}