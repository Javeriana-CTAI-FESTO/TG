import { Component, OnInit, ViewChild } from '@angular/core';
import { CdkDragDrop, moveItemInArray, transferArrayItem } from '@angular/cdk/drag-drop';
import { DashboardService, Estations } from '../dashboard.service';
import { NgForm } from '@angular/forms';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { MatDialog } from '@angular/material/dialog';
import { ToastrService } from 'ngx-toastr';
import { WorkplanServiceService, Workplan, pasos } from './workplan-service.service';
import { WorkplanDialogComponent } from './Dialogs/workplan-dialog/workplan-dialog.component';
import { EditWorkplanDialogComponent } from './Dialogs/edit-workplan-dialog/edit-workplan-dialog.component';


@Component({
  selector: 'app-posts',
  templateUrl: './posts.component.html',
  styleUrls: ['./posts.component.css']
})
export class PostsComponent implements OnInit {
  showWorkplan = false;

  nombre: string = "";
  id: string = "";
  descripcion: string = "";
  workplans: Workplan[] = [];
  dataSource: MatTableDataSource<Workplan>;
  columnas: string[] = ['ID', 'nombre','Descripcion', 'Operaciones'];
  @ViewChild(MatPaginator, { static: true }) paginator!: MatPaginator;

  constructor(private dashboardService: DashboardService, private workplanService: WorkplanServiceService, private toastr: ToastrService, private dialog: MatDialog) {
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

  drop(event: CdkDragDrop<string[]>) {
    if (event.previousContainer === event.container) {
      moveItemInArray(event.container.data, event.previousIndex, event.currentIndex);
    } else {
      transferArrayItem(event.previousContainer.data,
                        event.container.data,
                        event.previousIndex,
                        event.currentIndex);
                        
      if (event.container.id === 'done') {
        this.done[event.currentIndex].state = 'done';
      } else {
        this.todo[event.currentIndex].state = 'todo';
      }
    }
  }

  toggleWorkplan() {
    this.showWorkplan = !this.showWorkplan;
  }



  onSubmitWorkplan(form: NgForm) {
    const id = form.value.id;
    const name = form.value.nombre;
    const description = form.value.descripcion;
    const steps: pasos[] = [];

    for (let item of this.done) {
      if (item.firstStep === null || item.firstStep === undefined) {
        item.firstStep = false;
      }

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

    const newWorkplan: Workplan = {
      id: id,
      name: name,
      description: description,
      steps: steps
    };

    this.workplanService.addWorkPlan(newWorkplan);
    this.toggleWorkplan();
    this.done = [];
    form.reset();
    this.toastr.success('Workplan creado con éxito', 'Workplan creado');

    this.workplans = this.workplanService.getWorkplans();
    this.dataSource.data = this.workplans;
  }

  openDialog(workplan: Workplan): void {
    const dialogRef = this.dialog.open(WorkplanDialogComponent, {
      width: '500px',
      data: workplan
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
    });
  }

  openEditDialog(workplan: Workplan): void {
    const personaCopy = Object.assign({}, workplan); 
    const dialogRef = this.dialog.open(EditWorkplanDialogComponent, {
      data: personaCopy 
    });
  
    dialogRef.afterClosed().subscribe((result: any) => {
      if (result) {
        this.toastr.success('Persona editada', 'Éxito');
        this.workplanService.editarWorkPlan(workplan, result); 
        this.dataSource.data = this.workplanService.getWorkplans();
      }
    });
  }
  eliminarWorkplan(index: String) {
    if(!confirm('¿Estás seguro de eliminar este workplan?')) {
      this.toastr.error('Workplan no eliminado', 'Error');
      return;
    }
    else {
      this.workplanService.eliminarWorkPlan(index);
      this.toastr.success('Workplan eliminado', 'Éxito');
      this.dataSource.data = this.workplanService.getWorkplans();
    }
  }

}