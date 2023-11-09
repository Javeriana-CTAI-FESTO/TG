import { Component, OnInit } from '@angular/core';
import { ReportsServiceService } from './modules/reports/reports-service.service';
import { PiezasServiceService } from './modules/parts/piezas-service.service';
import { DashboardService } from './modules/dashboard.service';
import { LoginService } from './login/login.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title = 'front';

  constructor(private reportsService: ReportsServiceService, 
    private piezasService: PiezasServiceService,
    private dashboardService: DashboardService,
    private loginService: LoginService) { }

  ngOnInit(): void {
    this.reportsService.getReports().subscribe();
    this.reportsService.getReportsFails().subscribe();
    this.piezasService.getPiezasPorDefecto().subscribe();

    const authToken = localStorage.getItem('authToken') ?? '';
    const username = this.loginService.getUsername();
    this.dashboardService.getCedulaByUsername(username, authToken).subscribe((cedulaResponse: any) => {
      const cedula = cedulaResponse.cedula;
      this.dashboardService.getOrders(cedula, authToken).subscribe();
    });
    this.dashboardService.getParts().subscribe();
    this.dashboardService.getPartsForBuyer().subscribe();
  }
}