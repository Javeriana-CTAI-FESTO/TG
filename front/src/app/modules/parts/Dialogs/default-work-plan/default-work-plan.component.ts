import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Pieza } from '../../piezas-service.service';
import { WorkplanServiceService, Workplan } from 'src/app/modules/posts/workplan-service.service';

@Component({
  selector: 'app-default-work-plan',
  templateUrl: './default-work-plan.component.html',
  styleUrls: ['./default-work-plan.component.css']
})
export class DefaultWorkPlanComponent implements OnInit {

  pieza: Pieza;
  workplans: Workplan[] = [];

  constructor(
    public dialogRef: MatDialogRef<DefaultWorkPlanComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Pieza,
    private workplanService: WorkplanServiceService
  ) {
    this.pieza = Object.assign({}, data);
  }

  ngOnInit(): void {
    this.workplanService.getWorkPlansPorDefecto();
    this.workplans = this.workplanService.getWorkplans();
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  onEditClick(): void {
    this.dialogRef.close(this.pieza); 
  }

}