import { Component, OnInit, ViewChild } from '@angular/core';
import { DashboardService } from '../dashboard.service';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';

export interface Estations {
  name: string;
  position: number;
  code: number;
  state: string;
}
const ELEMENT_DATA: Estations[] = [
  { position: 1, name: 'Primera estación', code: 10079, state: 'True' },
  { position: 2, name: 'Segunda estación', code: 40026, state: 'True' },
  { position: 3, name: 'Tercera estación', code: 6941, state: 'True' },
  { position: 4, name: 'Cuarta estación', code: 90122, state: 'False' },
  { position: 5, name: 'Quinta estación', code: 10811, state: 'True' },
  { position: 6, name: 'Sexta estación', code: 120107, state: 'False' },
  { position: 7, name: 'Septima estación', code: 140067, state: 'False' },
  { position: 8, name: 'Octava estación', code: 159994, state: 'True' },
  { position: 9, name: 'Novena estación', code: 189984, state: 'True' },
  { position: 10, name: 'Decima estación', code: 201797, state: 'True' },
  { position: 11, name: 'Undecima estación', code: 229897, state: 'True' },
  { position: 12, name: 'Duodecima estación', code: 24305, state: 'False' },
  { position: 13, name: 'Decimotercera estación', code: 269815, state: 'True' },
  { position: 14, name: 'Decimocuarta estación', code: 280855, state: 'True' },
  { position: 15, name: 'Decimoquinta estación', code: 309738, state: 'False' }
];



@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  bigChart:any = [];
  cards:any = [];
  pieChart:any = [];

  displayedColumns: string[] = ['position', 'name', 'code', 'state'];
  dataSource = new MatTableDataSource<Estations>(ELEMENT_DATA);

  @ViewChild(MatPaginator, { static: true }) paginator!: MatPaginator;

  constructor(private dashboardService: DashboardService) { }

  ngOnInit() {
    this.bigChart = this.dashboardService.bigChart();
    this.cards = this.dashboardService.cards();
    this.pieChart = this.dashboardService.pieChart();

    this.dataSource.paginator = this.paginator;
  }

}