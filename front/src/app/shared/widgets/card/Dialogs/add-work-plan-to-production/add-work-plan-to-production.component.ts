import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Card } from 'src/app/modules/dashboard.service';
import { DashboardService, Part } from 'src/app/modules/dashboard.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-add-work-plan-to-production',
  templateUrl: './add-work-plan-to-production.component.html',
  styleUrls: ['./add-work-plan-to-production.component.css']
})
export class AddWorkPlanToProductionComponent implements OnInit {
  selectedPart: Part | undefined;
  parts: Part[] = [];
  quantity = 0;
  cards: Card[] = [];

  constructor(
    public dialogRef: MatDialogRef<AddWorkPlanToProductionComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private DashboradService: DashboardService,
    private toastr: ToastrService
  ) {}

  ngOnInit(): void {
    this.DashboradService.getParts().subscribe(
      (parts: Part[]) => {
        this.parts = parts;
      },
      (error: any) => {
        console.error(error);
      }
    );
  }

  


  addPart(): void {
    const part = this.selectedPart;
    const quantity = this.quantity;
    const startingId = this.cards.length;
    if (part) {
      let count = 0;
      const placeOrder = () => {
        if (count < quantity) {
          this.DashboradService.placeNewOrder(part.partNumber, 0, 1).subscribe(response => {
            this.cards.push({
              id: part?.partNumber || startingId + count,
              idworkPlan: part?.workPlanNumber|| startingId + count,
              title: part?.description || '',
              OrderNumber: response.orderNumber,
              imageUrl: '../../../../assets/alexandre-debieve-FO7JIlwjOtU-unsplash.jpg'
            });
            count++;
            placeOrder();
          });
        } else {
          this.dialogRef.close(this.cards);
        }
      };
      placeOrder();
    }
  }
  
  
  

  onNoClick(): void {
    this.dialogRef.close();
  }
}