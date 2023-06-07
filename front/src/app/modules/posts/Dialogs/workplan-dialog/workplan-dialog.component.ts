import { Component, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Workplan } from '../../workplan-service.service';

@Component({
  selector: 'app-workplan-dialog',
  templateUrl: './workplan-dialog.component.html',
  styleUrls: ['./workplan-dialog.component.css']
})
export class WorkplanDialogComponent {

  constructor(
    public dialogRef: MatDialogRef<WorkplanDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Workplan) {}

  onNoClick(): void {
    this.dialogRef.close();
  }

}