import { Component, Inject, Output, EventEmitter } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { PartWithQuantity } from '../../buy.component';
import { DashboardService } from 'src/app/modules/dashboard.service';
import { LoginService } from 'src/app/login/login.service';
import { ToastrService } from 'ngx-toastr';

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

  async addPart(): Promise<void> {
    this.isLoading = true;
    const authToken = localStorage.getItem('authToken') ?? '';
    const username = this.loginService.getUsername();

    const processPartWithQuantity = async (partWithQuantity: PartWithQuantity) => {
      const quantity = partWithQuantity.quantity;
      let count = 0;
      while (count < quantity) {
        await this.dashboardService.getCedulaByUsername(username, authToken).toPromise().then((cedulaResponse: any) => {
          const orderData = {
            id_part: partWithQuantity.partNumber,
            id_workPlan: partWithQuantity.workPlanNumber,
            cliente_Cedula: cedulaResponse.cedula,
            title: partWithQuantity.description,
            orderNumber: 4
          };
          return this.dashboardService.saveOrder(orderData, authToken).toPromise();
        });
        count++;
      }
    };

    try {
      await this.data.purchases.reduce((promiseChain, partWithQuantity) => {
        return promiseChain.then(() => processPartWithQuantity(partWithQuantity));
      }, Promise.resolve());

      this.toastr.success('The operation was completed successfully.', '¡Succes!');
    } catch (error) {
      this.toastr.error('The operation failed.', 'Error');
      console.error(error);
    } finally {
      this.dialogRef.close();
      this.isLoading = false;
    }
  }

  emptyShoppingCart(): void {
    this.emptyCart.emit();
    this.dialogRef.close();
  }

}