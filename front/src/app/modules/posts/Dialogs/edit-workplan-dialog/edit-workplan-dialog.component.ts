import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Workplan, pasos } from '../../workplan-service.service';
import { CdkDragDrop, moveItemInArray, transferArrayItem } from '@angular/cdk/drag-drop';
import { DashboardService } from 'src/app/modules/dashboard.service';
@Component({
  selector: 'app-edit-workplan-dialog',
  templateUrl: './edit-workplan-dialog.component.html',
  styleUrls: ['./edit-workplan-dialog.component.css']
})
export class EditWorkplanDialogComponent implements OnInit {
  workplan: Workplan;

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

  constructor(private dashboardService: DashboardService,
    public dialogRef: MatDialogRef<EditWorkplanDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Workplan
  ) {
    this.workplan = Object.assign({}, data);
  }
  ngOnInit(): void {
    this.dashboardService.getStations().subscribe(data => {
      this.todo = data;
    });
    this.done = this.workplan.steps.map(step => {
      const item = {
        step: step.step,
        nextStep: step.nextStep,
        firstStep: step.firstStep,
        endStep: step.endStep,
        name: step.resources, // asignar steps.resources a item.name
        operation: step.operation,
        TransportTime: step.TransportTime,
        WorkingTime: step.WorkingTime,
        energy: step.energy,
        energy2: step.energy2
      };
      return item;
    });
  }

  onNoClick(): void {
    this.dialogRef.close();
  }


  onEditClick(): void {
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
      console.log(step);
      steps.push(step);
      this.workplan.steps = steps;
      this.dialogRef.close(this.workplan);
    }
  }

}
