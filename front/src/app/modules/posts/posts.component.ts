import { Component, OnInit, ViewChild } from '@angular/core';
import { CdkDragDrop, moveItemInArray, transferArrayItem } from '@angular/cdk/drag-drop';
import { DashboardService, Estations } from '../dashboard.service';

import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { MatDialog } from '@angular/material/dialog';
import { Subscription } from 'rxjs';
import { ToastrService } from 'ngx-toastr';
import { WorkplanServiceService, Workplan } from './workplan-service.service';

@Component({
  selector: 'app-posts',
  templateUrl: './posts.component.html',
  styleUrls: ['./posts.component.css']
})
export class PostsComponent implements OnInit{
  showWorkplan = false;

  workplans: Workplan[] = [];
  dataSource: MatTableDataSource<Workplan>;
  columnas: string[] = ['ID', 'nombre'];
  @ViewChild(MatPaginator, { static: true }) paginator!: MatPaginator;

  constructor(private dashboardService: DashboardService, private workplanService: WorkplanServiceService, private toastr: ToastrService) { 
    this.dataSource = new MatTableDataSource<Workplan>(this.workplans);
  }

  ngOnInit(): void {
    this.dataSource.paginator = this.paginator;
    this.workplanService.getWorkPlansPorDefecto();
    this.workplans = this.workplanService.getWorkplans();
    this.dataSource.data = this.workplans;
    this.dashboardService.getStations().subscribe(data => {
      this.todo = data;
      console.log(this.todo);
    });
    
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }


  todo: any[] = [
    
  ];

  done: any[] = [
  ];

  drop(event: CdkDragDrop<any[]>) {
    if (event.previousContainer === event.container) {
      moveItemInArray(event.container.data, event.previousIndex, event.currentIndex);
    } else {
      transferArrayItem(event.previousContainer.data,
        event.container.data,
        event.previousIndex,
        event.currentIndex);
    }
  }
  toggleWorkplan() {
    this.showWorkplan = !this.showWorkplan;
  }

}
