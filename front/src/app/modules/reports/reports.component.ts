import { Component, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { OnInit } from '@angular/core';
import { ReportsServiceService } from './reports-service.service';
import { ResponseData } from './reports-service.service';
import { MatTableDataSource } from '@angular/material/table';
import { MatDialog } from '@angular/material/dialog';
import { ReportDialogComponent } from './Dialogs/report-dialog/report-dialog.component';
import { ResourceDialogComponent } from './Dialogs/resource-dialog/resource-dialog.component';
@Component({
  selector: 'app-reports',
  templateUrl: './reports.component.html',
  styleUrls: ['./reports.component.css']
})
export class ReportsComponent implements OnInit {
  dataSource: MatTableDataSource<ResponseData>;
  failDataSource: MatTableDataSource<ResponseData>;
  displayedColumns: string[] = ['report','resource','timestamp','operations'];
  failDisplayedColumns: string[] = ['report', 'resource', 'timestamp', 'operations'];
  filterValue = '';
  filterValueFail = '';

  @ViewChild(MatPaginator, {static: true}) paginator!: MatPaginator;
  @ViewChild('failPaginator', {static: true}) failPaginator!: MatPaginator;
  constructor(private reportsService: ReportsServiceService, private dialog: MatDialog) {
    this.dataSource = new MatTableDataSource<ResponseData>();
    this.dataSource.filterPredicate = (data: ResponseData, filter: string) => {
      const dataStr = data.resource.name.toLowerCase() + data.report.id.toString().toLowerCase();
      return dataStr.indexOf(filter) !== -1;
    };
  
    this.failDataSource = new MatTableDataSource<ResponseData>();
    this.failDataSource.filterPredicate = (data: ResponseData, filter: string) => {
      const dataStr = data.resource.name.toLowerCase() + data.report.id.toString().toLowerCase();
      return dataStr.indexOf(filter) !== -1;
    };
  }

  ngOnInit(): void {
    this.reportsService.getReports().subscribe((response: ResponseData[]) => {
      this.dataSource.data = response;
      this.dataSource.paginator = this.paginator;
      this.dataSource.paginator.length = response.length;
    });
  
    this.reportsService.getReportsFails().subscribe((response: ResponseData[]) => {
      this.failDataSource.data = response;
      this.failDataSource.paginator = this.failPaginator;
      this.failDataSource.paginator.length = response.length;
    });
    
    this.failDataSource.filterPredicate = (data: ResponseData, filter: string) => {
      const dataStr = data.resource.name.toLowerCase() + data.report.id.toString().toLowerCase();
      return dataStr.indexOf(filter) !== -1;
    };
  }
  
  ngAfterViewInit(): void {
    this.dataSource.paginator = this.paginator;
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.filterValue = filterValue.trim().toLowerCase();
    this.dataSource.filter = this.filterValue;
  }
  applyFailFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.failDataSource.filter = filterValue.trim().toLowerCase();
  }
  openDialogReport(report: any) {
    const dialogRef = this.dialog.open(ReportDialogComponent, {
      data: { report }
    });
  }
  openDialogResource(resource: any) {
    const dialogRef = this.dialog.open(ResourceDialogComponent, {
      data: { resource }
    });
  }
}
