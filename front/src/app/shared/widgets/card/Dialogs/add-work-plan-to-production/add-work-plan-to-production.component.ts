import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Card } from 'src/app/modules/dashboard.service';
import { DashboardService, Part } from 'src/app/modules/dashboard.service';
import { ToastrService } from 'ngx-toastr';
import { HttpClient } from '@angular/common/http';
import { LoginService } from 'src/app/login/login.service';
@Component({
  selector: 'app-add-work-plan-to-production',
  templateUrl: './add-work-plan-to-production.component.html',
  styleUrls: ['./add-work-plan-to-production.component.css']
})
export class AddWorkPlanToProductionComponent implements OnInit {
  isLoading = false;

  selectedPart: Part | undefined;
  parts: Part[] = [];
  quantity = 0;
  cards: Card[] = [];

  constructor(
    public dialogRef: MatDialogRef<AddWorkPlanToProductionComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private DashboradService: DashboardService,
    private loginService: LoginService
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
    this.isLoading = true;
    const part = this.selectedPart;
    const quantity = this.quantity;
    const startingId = this.cards.length;
    const authToken = localStorage.getItem('authToken') ?? '';
    const username = this.loginService.getUsername();
    if (part) {
      let count = 0;
      const placeOrder = () => {
        if (count < quantity) {
            this.DashboradService.getCedulaByUsername(username, authToken).subscribe((cedulaResponse: any) => {
              const orderData = {
                id_part: part.partNumber,
                id_workPlan: part.workPlanNumber,
                cliente_Cedula: cedulaResponse.cedula,
                title: part.description,
                orderNumber: 4
              };
              this.DashboradService.saveOrder(orderData, authToken).subscribe((response: any) => {
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
            });
        } else {
          this.dialogRef.close(this.cards);
          this.isLoading = false;
        }
      };
      placeOrder();
    }
  }
  
  

  onNoClick(): void {
    this.dialogRef.close();
  }
}

