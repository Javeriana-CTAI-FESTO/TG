import { Component, Inject} from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Pieza } from '../../piezas-service.service';

@Component({
  selector: 'app-default-work-plan',
  templateUrl: './default-work-plan.component.html',
  styleUrls: ['./default-work-plan.component.css']
})
export class DefaultWorkPlanComponent {

  constructor(
    public dialogRef: MatDialogRef<DefaultWorkPlanComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Pieza) {
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

}