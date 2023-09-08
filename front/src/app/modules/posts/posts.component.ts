import { Component, OnInit, ViewChild } from '@angular/core';
import { CdkDragDrop, moveItemInArray, transferArrayItem } from '@angular/cdk/drag-drop';
import { DashboardService, Estations } from '../dashboard.service';
import { NgForm } from '@angular/forms';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { MatDialog } from '@angular/material/dialog';
import { ToastrService } from 'ngx-toastr';
import { WorkplanServiceService, Workplan } from './workplan-service.service';
import { WorkplanDialogComponent } from './Dialogs/workplan-dialog/workplan-dialog.component';
import { EditWorkplanDialogComponent } from './Dialogs/edit-workplan-dialog/edit-workplan-dialog.component';
import { Pipe, PipeTransform } from '@angular/core';
import { PageEvent } from '@angular/material/paginator';

@Component({
  selector: 'app-posts',
  templateUrl: './posts.component.html',
  styleUrls: ['./posts.component.css']
})

export class PostsComponent implements OnInit {
  showWorkplan = false;

  workPlanNumber: number = 0;
  description: string = "";
  descripcion: string = "";
  workPlanType: number = 0;
  shortDescription: string = "";
  pictureNumber: number = 0;
  partNumber: number = 0;
  workPlanTypes: { key: string, value: string }[] = [];
  workplans: Workplan[] = [];
  filteredTodo: any[] = [];
  itemsPerPage = 5; // Puedes cambiar este valor a lo que necesites
  totalItems = 0; // Puedes cambiar este valor a lo que necesites


  dataSource: MatTableDataSource<Workplan>;
  columnas: string[] = ['ID', 'Picture', 'Description', 'Type', 'Operaciones'];
  @ViewChild(MatPaginator, { static: true }) paginator!: MatPaginator;

  constructor(private dashboardService: DashboardService, private workplanService: WorkplanServiceService, private toastr: ToastrService, private dialog: MatDialog) {
    this.dataSource = new MatTableDataSource<Workplan>([]);

  }

  ngOnInit(): void {
    this.itemsPerPage = 5;
    this.dataSource.paginator = this.paginator;
    this.workplanService.getWorkPlansPorDefecto().subscribe(
      (workplans: Workplan[]) => {
        this.workplans = workplans;
        this.dataSource.data = this.workplans;
      },
      (error: any) => {
        console.error(error);
      }
    );
  
    this.workplanService.getWorkPlanTypes().subscribe(data => {
      for (const key in data) {
        this.workPlanTypes.push({ key: key, value: data[key] });
      }
    });
  
    this.workplanService.getSteps().subscribe(data => {
      this.todo = data;
      this.filteredTodo = data;
      this.totalItems = this.todo.length;
      this.filteredTodo = this.todo.slice(0, this.itemsPerPage);
    });
  }


  moveItem(item: any, sourceList: any[], destList: any[]) {
    const index = sourceList.indexOf(item);
    if (index >= 0) {
        sourceList.splice(index, 1);
        destList.push(item);
    }
}


  pageEvent(event: PageEvent) {
    const pageIndex = event.pageIndex;
    const pageSize = event.pageSize;

    const startIndex = pageIndex * pageSize;
    const endIndex = startIndex + pageSize;

    this.filteredTodo = this.todo.slice(startIndex, endIndex);
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
 
  applyFilterTodo(event: Event) {
    // Obtener el valor del filtro
    const filterValue = (event.target as HTMLInputElement).value;

    // Filtrar los datos
    this.filteredTodo = this.todo.filter(item => item.description.toLowerCase().includes(filterValue.toLowerCase()));

    // Actualizar el paginador
    this.totalItems = this.filteredTodo.length;
    this.filteredTodo = this.filteredTodo.slice(0, this.itemsPerPage);
}


  toggleWorkplan() {
    this.showWorkplan = !this.showWorkplan;
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


  onSubmitWorkplan(form: NgForm) {
    const workPlanNumber = this.workPlanNumber;
    const description = this.description;
    const workPlanType = this.workPlanType;
    const shortDescription = this.shortDescription;
    const pictureNumber = this.pictureNumber;
    const partNumber = this.partNumber;
    const steps: Step[] = [];

    

    let minThisStepNumber = Infinity;
    for (let item of this.done) {
      if (item.thisStepNumber < minThisStepNumber) {
        minThisStepNumber = item.thisStepNumber;
      }


    }

    for (let item of this.done) {
      item.firstStep = (item.thisStepNumber === minThisStepNumber);
      if (item.errorStep === null || item.errorStep === undefined) {
        item.errorStep = false;
      }

      const step: Step = {
        definedStepWorkPlanNumber: item.workPlan.workPlanNumber,
        definedStepNumber: item.stepNumber,
        thisStepNumber: item.thisStepNumber,
        nextStepNumber: item.nextStep,
        firstStep: item.firstStep,
        errorStep: item.errorStep,
        errorStepNumber: item.errorStepNumber
      };

      steps.push(step);

    }

    const newWorkplan: WorkPlan = {
      workPlanNumber: workPlanNumber,
      description: description,
      workPlanType: workPlanType,
      shortDescription: shortDescription,
      pictureNumber: pictureNumber,
      partNumber: partNumber,
      steps: steps
    };

    this.workplanService.addWorkPlan(newWorkplan).subscribe(
      response => {
        this.toastr.success('Workplan creado con éxito', 'Workplan creado');
        console.log(response);

        this.workplanService.getWorkPlansPorDefecto().subscribe(
          (workplans: Workplan[]) => {
            this.workplans = workplans;
            this.dataSource.data = this.workplans;
          }
        );
      },
      error => {
        console.error(error);
      }
    );
    
    
    this.toggleWorkplan();
    this.done = [];
    form.reset();

  }



  /*openEditDialog(workplan: Workplan): void {
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
  }*/

}

export interface WorkPlan {
  workPlanNumber: number;
  description: string;
  workPlanType: number;
  shortDescription: string;
  pictureNumber: number;
  partNumber: number;
  steps: Step[];
}

interface Step {
  definedStepWorkPlanNumber: number;
  definedStepNumber: number;
  thisStepNumber: number;
  nextStepNumber: number;
  firstStep: boolean;
  errorStep: boolean;
  errorStepNumber: number;
}