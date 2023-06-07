import { Component, OnInit, ViewChild } from '@angular/core';
import { CdkDragDrop, moveItemInArray, transferArrayItem } from '@angular/cdk/drag-drop';
import { DashboardService, Estations } from '../dashboard.service';
import { NgForm } from '@angular/forms';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { MatDialog } from '@angular/material/dialog';
import { Subscription } from 'rxjs';
import { ToastrService } from 'ngx-toastr';
import { WorkplanServiceService, Workplan, pasos } from './workplan-service.service';

@Component({
  selector: 'app-posts',
  templateUrl: './posts.component.html',
  styleUrls: ['./posts.component.css']
})
export class PostsComponent implements OnInit{
  showWorkplan = false;

  nombre: string = "";
  id: string = "";
  descripcion: string = "";
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
  
  addWorkplan(id: string, name: string, description: string, steps: pasos[]) {
    const newWorkplan: Workplan = { id, name, description, steps };
    console.log(newWorkplan);
    this.workplans.push(newWorkplan);
    this.dataSource.data = this.workplans;
  }

  onSubmitWorkplan(form: NgForm) {
    const id = form.value.id;
    const name = form.value.nombre;
    const description = form.value.descripcion;
    const steps: pasos[] = [];
  
    // Iterate over the done list and create a new pasos object for each item
    for (let item of this.done) {
      // Check if the value of item.firstStep is null or undefined, and if so, set it to false
      if (item.firstStep === null || item.firstStep === undefined) {
        item.firstStep = false;
      }
  
      // Check if the value of item.endStep is null or undefined, and if so, set it to false
      if (item.endStep === null || item.endStep === undefined) {
        item.endStep = false;
      }
  
      const step: pasos = {
        step: item.step,
        nextStep: item.nextStep,
        firstStep: item.firstStep,
        endStep: item.endStep,
        resources: item.name,
        operation: item.operation,
        TransportTime: item.TransportTime,
        WorkingTime: item.WorkingTime,
        energy: item.energy,
        energy2: item.energy2
      };
      steps.push(step);
    }
  
    this.addWorkplan(id, name, description, steps);
    this.toggleWorkplan();
    this.done = [];
    form.reset();
    this.toastr.success('Workplan creado con éxito', 'Workplan creado');
  }

}
