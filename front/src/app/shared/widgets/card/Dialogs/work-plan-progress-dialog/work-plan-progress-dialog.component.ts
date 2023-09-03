import { Component, Inject } from '@angular/core';
import { MatDialogRef,MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-work-plan-progress-dialog',
  templateUrl: './work-plan-progress-dialog.component.html',
  styleUrls: ['./work-plan-progress-dialog.component.css']
})
export class WorkPlanProgressDialogComponent {

  steps:any[] = [];

  constructor(
    public dialogRef: MatDialogRef<WorkPlanProgressDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any) {
    this.steps=data.steps.map((step: any, index: any) => ({
      ...step,
      progressValue: index === 0 ? 75 : 0
    }));
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

}
