import { Component, Inject, Output, EventEmitter } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { PartWithQuantity } from '../../buy.component';
import { DashboardService } from 'src/app/modules/dashboard.service';
import { LoginService } from 'src/app/login/login.service';
import { ToastrService } from 'ngx-toastr';
import { from } from 'rxjs';
import { mergeMap } from 'rxjs/operators';


interface DialogData {
  purchases: PartWithQuantity[];
}

@Component({
  selector: 'app-purchase-history-dialog',
  templateUrl: './purchase-history-dialog.component.html',
  styleUrls: ['./purchase-history-dialog.component.css']
})
export class PurchaseHistoryDialogComponent {
  @Output() emptyCart = new EventEmitter<void>();

  isLoading = false;
  formCompleted = false;

  constructor(
    public dialogRef: MatDialogRef<PurchaseHistoryDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: DialogData,
    private dashboardService: DashboardService,
    private loginService: LoginService,
    private toastr: ToastrService
  ) { }

  onNoClick(): void {
    this.dialogRef.close();
  }
  

  checkFormCompletion(): void {
    const name = (document.getElementById('inputName') as HTMLInputElement).value;
    const card = (document.getElementById('inputCard') as HTMLInputElement).value;
    const expiration = (document.getElementById('inputExpiration') as HTMLInputElement).value;

    if (name && card && expiration) {
      this.formCompleted = true;
    } else {
      this.formCompleted = false;
    }
  }

  async addPart(): Promise<void> {
    this.isLoading = true;
    const authToken = localStorage.getItem('authToken') ?? '';
    const username = this.loginService.getUsername();
  
    const tasks = this.data.purchases.flatMap(partWithQuantity => {
      return Array(partWithQuantity.quantity).fill(null).map(() => {
        return this.dashboardService.getCedulaByUsername(username, authToken).pipe(
          mergeMap((cedulaResponse: any) => {
            const orderData = {
              id_part: partWithQuantity.partNumber,
              id_workPlan: partWithQuantity.workPlanNumber,
              cliente_Cedula: cedulaResponse.cedula,
              title: partWithQuantity.description,
              orderNumber: 4,
              image_filePath: partWithQuantity.modifiedPictureName
            };
            return this.dashboardService.saveOrder(orderData, authToken);
          })
        ).toPromise();
      });
    });
  
    try {
      await Promise.all(tasks);
  
      this.toastr.success('The operation was completed successfully.', '¡Succes!');
    } catch (error) {
      this.toastr.error('The operation failed.', 'Error');
      console.error(error);
    } finally {
      this.emptyCart.emit();
      this.dialogRef.close();
      this.isLoading = false;
    }

   /* const tasks = this.data.purchases.flatMap(partWithQuantity => {
      console.log(partWithQuantity);
      console.log(partWithQuantity.modifiedPictureName);
    });*/
  }

  emptyShoppingCart(): void {
    this.emptyCart.emit();
    this.dialogRef.close();
  }

}