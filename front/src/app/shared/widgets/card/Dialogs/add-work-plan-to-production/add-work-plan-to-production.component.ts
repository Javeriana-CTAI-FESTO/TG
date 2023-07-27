import { Component, Inject } from '@angular/core';
import { MatDialogRef,MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-add-work-plan-to-production',
  templateUrl: './add-work-plan-to-production.component.html',
  styleUrls: ['./add-work-plan-to-production.component.css']
})
export class AddWorkPlanToProductionComponent {

  constructor(
    public dialogRef: MatDialogRef<AddWorkPlanToProductionComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any) {}
  
  onNoClick(): void {
    this.dialogRef.close();
  }

}
