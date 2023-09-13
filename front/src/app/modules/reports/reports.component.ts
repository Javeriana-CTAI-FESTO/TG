import { Component, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { OnInit } from '@angular/core';
import { ReportsServiceService } from './reports-service.service';
import { ResponseData } from './reports-service.service';
import { MatTableDataSource } from '@angular/material/table';
import { PageEvent } from '@angular/material/paginator';
@Component({
  selector: 'app-reports',
  templateUrl: './reports.component.html',
  styleUrls: ['./reports.component.css']
})
export class ReportsComponent implements OnInit {
  dataSource: MatTableDataSource<ResponseData>;
  displayedColumns: string[] = ['report','resource','timestamp','operations'];
  filterValue = '';

  @ViewChild(MatPaginator, {static: true}) paginator!: MatPaginator;

  constructor(private reportsService: ReportsServiceService) {
    this.dataSource = new MatTableDataSource<ResponseData>();
    this.dataSource.filterPredicate = (data: ResponseData, filter: string) => {
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
  }
  
  ngAfterViewInit(): void {
    this.dataSource.paginator = this.paginator;
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.filterValue = filterValue.trim().toLowerCase();
    this.dataSource.filter = this.filterValue;
  }
}