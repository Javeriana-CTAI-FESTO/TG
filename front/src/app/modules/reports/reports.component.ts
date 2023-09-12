import { Component } from '@angular/core';
import { OnInit } from '@angular/core';
import { ReportsServiceService } from './reports-service.service';
@Component({
  selector: 'app-reports',
  templateUrl: './reports.component.html',
  styleUrls: ['./reports.component.css']
})
export class ReportsComponent implements OnInit {

  constructor(private reportsService:ReportsServiceService ) { }
  ngOnInit(): void {
    this.reportsService.getFails();
  }

}
