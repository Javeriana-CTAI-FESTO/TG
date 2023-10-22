import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Card } from 'src/app/modules/dashboard.service';
import { DashboardService, Part } from 'src/app/modules/dashboard.service';
import { ToastrService } from 'ngx-toastr';
import { HttpClient } from '@angular/common/http';
import { LoginService } from 'src/app/login/login.service';
import { from } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

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
      const tasks = Array(quantity).fill(null).map(() => {
        return this.DashboradService.getCedulaByUsername(username, authToken).pipe(
          mergeMap((cedulaResponse: any) => {
            const orderData = {
              id_part: part.partNumber,
              id_workPlan: part.workPlanNumber,
              cliente_Cedula: cedulaResponse.cedula,
              title: part.description,
              orderNumber: 4,
              image_filePath: part.picture
            };
            return this.DashboradService.saveOrder(orderData, authToken);
          }),
          mergeMap((response: any) => {
            this.cards.push({
              id: part?.partNumber || startingId + this.cards.length,
              idworkPlan: part?.workPlanNumber || startingId + this.cards.length,
              title: part?.description || '',
              OrderNumber: response.orderNumber,
              imageUrl: part?.picture || ''
            });
            return from(this.cards);
          })
        ).toPromise();
      });
  
      Promise.all(tasks).then(() => {
        this.dialogRef.close(this.cards);
        this.isLoading = false;
      });
    }
  }
  
  

  onNoClick(): void {
    this.dialogRef.close();
  }
}

