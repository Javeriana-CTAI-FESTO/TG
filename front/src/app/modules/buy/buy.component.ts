import { Component, OnInit } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { DashboardService, Part } from '../dashboard.service';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { PurchaseHistoryDialogComponent } from './Dialogs/purchase-history-dialog/purchase-history-dialog.component';
@Component({
  selector: 'app-buy',
  templateUrl: './buy.component.html',
  styleUrls: ['./buy.component.css']
})
export class BuyComponent implements OnInit{
  quantity: number = 0;
  searchTerm: string = '';
  parts: PartWithQuantity[] = [];
  filteredParts: PartWithQuantity[] = [];
  purchaseCount: number = 0;
  purchases: PartWithQuantity[] = [];
  constructor(
    private toastr: ToastrService,
    private dashboardService: DashboardService,
    public dialog: MatDialog
  ) { }

  ngOnInit(): void {
    this.dashboardService.getParts().subscribe(
        (parts: Part[]) => {
            this.parts = parts.map(part => ({ ...part, quantity: 0 }));
            this.filteredParts = this.parts;
            console.log(this.parts);
        },
        (error: any) => {
            console.error(error);
        }
    );
  }

  resetValues(): void {
    this.quantity = 0;
    this.purchaseCount = 0;
    this.purchases = [];
  }
  
  search(): void {
    if (this.searchTerm) {
      this.filteredParts = this.parts.filter(part => 
        part.description.toLowerCase().includes(this.searchTerm.toLowerCase()) ||
        part.partNumber.toString().toLowerCase().includes(this.searchTerm.toLowerCase())
      );
    } else {
      this.filteredParts = this.parts;
    }
  }

  buy(part: PartWithQuantity): void {
    if (part.quantity > 0) {
      this.purchaseCount += part.quantity;
      this.purchases.push({ ...part, quantity: part.quantity });
      this.toastr.success(`${part.quantity} of ${part.description} were added to the shopping cart.`, '¡Succecs!');
      part.quantity = 0;
    }
}
openDialog(): void {
  const dialogRef = this.dialog.open(PurchaseHistoryDialogComponent, {
    width: '250px',
    data: { purchases: this.purchases }
  });

  dialogRef.componentInstance.emptyCart.subscribe(() => {
    this.resetValues();
  });

  dialogRef.afterClosed().subscribe(result => {
    console.log('The dialog was closed');
  });
}
}

export interface PartWithQuantity extends Part {
  quantity: number;
}