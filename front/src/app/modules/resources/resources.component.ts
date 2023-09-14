import { Component, OnInit, ViewChild } from '@angular/core';
import { DashboardService, Estations } from '../dashboard.service';
import { MatPaginator } from '@angular/material/paginator';
import { MatDialog } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';
import { ResourceInfoDialogComponent } from './Dialogs/resource-info-dialog/resource-info-dialog.component';
import { ResourceReportsDialogComponent } from './Dialogs/resource-reports-dialog/resource-reports-dialog.component';
import { ResourceReportFailsDialogComponent } from './Dialogs/resource-report-fails-dialog/resource-report-fails-dialog.component';
import { LoginService } from 'src/app/login/login.service';
@Component({
  selector: 'app-resources',
  templateUrl: './resources.component.html',
  styleUrls: ['./resources.component.css']
})
export class ResourcesComponent implements OnInit {
  rol: string = '';
  estations: Estations[] = [];
  constructor(private dashboardService: DashboardService, private dialog: MatDialog, private loginService: LoginService) { }
  displayedColumns: string[] = ['id', 'name', 'plcType', 'ip','operations' ];
  dataSource = new MatTableDataSource<Estations>();

  @ViewChild(MatPaginator, { static: true }) paginator!: MatPaginator;


  ngOnInit(): void {
    this.dataSource.paginator = this.paginator;
    this.dashboardService.getStations().subscribe(data => {
      this.dataSource.data = data;
    });
    this.rol = this.loginService.getRole();
  }
  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase(); 
  }
  openDialogInfo(resource: Estations) {
    const dialogRef = this.dialog.open(ResourceInfoDialogComponent, {
      data: resource
    });
  }
  openDialogReports(id: number) {
    const dialogRef = this.dialog.open(ResourceReportsDialogComponent, {
      data: id
    });
  }
  openDialogFails(id: number) {
    const dialogRef = this.dialog.open(ResourceReportFailsDialogComponent, {
      data: id
    });
  }
  
}