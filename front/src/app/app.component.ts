import { Component, OnInit } from '@angular/core';
import { ReportsServiceService } from './modules/reports/reports-service.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title = 'front';

  constructor(private reportsService: ReportsServiceService) { }

  ngOnInit(): void {
  }
}