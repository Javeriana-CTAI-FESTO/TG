import { Component, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-resource-info-dialog',
  templateUrl: './resource-info-dialog.component.html',
  styleUrls: ['./resource-info-dialog.component.css']
})
export class ResourceInfoDialogComponent {
  constructor(public dialogRef: MatDialogRef<ResourceInfoDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any) { }
  onNoClick(): void {
    this.dialogRef.close();
  }

}
