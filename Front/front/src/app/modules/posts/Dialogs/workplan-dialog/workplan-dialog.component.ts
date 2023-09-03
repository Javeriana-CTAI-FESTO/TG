import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { WorkplanServiceService } from '../../workplan-service.service';

@Component({
  selector: 'app-workplan-dialog',
  templateUrl: './workplan-dialog.component.html',
  styleUrls: ['./workplan-dialog.component.css']
})
export class WorkplanDialogComponent implements OnInit {
  workplan: any;
  workplanId: number;

  constructor(
    public dialogRef: MatDialogRef<WorkplanDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private workplanService: WorkplanServiceService
  ) {
    this.workplanId = data.workPlanNumber;
  }

  ngOnInit(): void {
    this.getWorkplanById(this.workplanId);
  }

  getWorkplanById(workplanId: number): void {
    this.workplanService.getWorkplanById(workplanId).subscribe(workplan => {
      this.workplan = workplan;
      console.log(this.workplan);
    });
  }
  onNoClick(): void {
    this.dialogRef.close();
  }
}