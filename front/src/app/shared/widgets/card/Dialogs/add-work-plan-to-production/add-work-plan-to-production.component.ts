import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef,MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Card } from 'src/app/modules/dashboard.service';
import { WorkplanServiceService, Workplan } from 'src/app/modules/posts/workplan-service.service';
@Component({
  selector: 'app-add-work-plan-to-production',
  templateUrl: './add-work-plan-to-production.component.html',
  styleUrls: ['./add-work-plan-to-production.component.css']
})
export class AddWorkPlanToProductionComponent implements OnInit{

  selectedWorkplan: Workplan | undefined;
  workplans: Workplan[] = [];
  quantity: number = 0;
  cards: Card[] = [];

  constructor(
    public dialogRef: MatDialogRef<AddWorkPlanToProductionComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private workplanservice: WorkplanServiceService) {}
  
  onNoClick(): void {
    this.dialogRef.close();
  }
  ngOnInit(): void {
    this.workplans = this.workplanservice.getWorkplans();
  }

  addWorkplan() {
    const workplan = this.selectedWorkplan;
    const quantity = this.quantity;
    const startingId = this.cards.length;
    for (let i = 0; i < quantity; i++) {
      this.cards.push({
        id: workplan?.id || startingId + i,
        title: workplan?.name || '',
        state: 'Incomplete',
        imageUrl: '../../../../assets/alexandre-debieve-FO7JIlwjOtU-unsplash.jpg'
      });
    }
    this.dialogRef.close(this.cards);
  }

}
