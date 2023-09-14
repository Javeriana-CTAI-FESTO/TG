import { Component, Inject, OnInit, ViewChild } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { ReportsServiceService } from 'src/app/modules/reports/reports-service.service';
import { Report } from 'src/app/modules/reports/reports-service.service';

@Component({
  selector: 'app-resource-report-fails-dialog',
  templateUrl: './resource-report-fails-dialog.component.html',
  styleUrls: ['./resource-report-fails-dialog.component.css']
})
export class ResourceReportFailsDialogComponent implements OnInit {
  dataSource: MatTableDataSource<Report>;
  displayedColumns: string[] = ['id', 'timestamp', 'automaticMode', 'manualMode', 'busy', 'reset', 'errorL0', 'errorL1', 'errorL2'];
  @ViewChild(MatPaginator) paginator!: MatPaginator;

  constructor(
    public dialogRef: MatDialogRef<ResourceReportFailsDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private reportsService: ReportsServiceService
  ) {
    this.dataSource = new MatTableDataSource<Report>([]);
  }
  ngOnInit(): void { }

  ngAfterViewInit(): void {
    this.reportsService.getReportFailsById(this.data).subscribe((response: Report[]) => {
      this.dataSource.data = response;
      this.dataSource.paginator = this.paginator;
    });
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase(); 
  }

  onNoClick(): void {
    this.dialogRef.close();
  }
}
