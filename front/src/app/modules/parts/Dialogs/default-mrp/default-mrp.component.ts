import { Component, Inject,OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Pieza } from '../../piezas-service.service';

@Component({
  selector: 'app-default-mrp',
  templateUrl: './default-mrp.component.html',
  styleUrls: ['./default-mrp.component.css']
})
export class DefaultMRPComponent{

  constructor(
    public dialogRef: MatDialogRef<DefaultMRPComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Pieza
  ) { 
  }
  onNoClick(): void {
    this.dialogRef.close();
  }
}
