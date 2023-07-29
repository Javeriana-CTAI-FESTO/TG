import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Pieza } from '../../piezas-service.service';

@Component({
  selector: 'app-default-other-settings',
  templateUrl: './default-other-settings.component.html',
  styleUrls: ['./default-other-settings.component.css']
})
export class DefaultOtherSettingsComponent implements OnInit{

  pieza: Pieza;
  constructor(
    public dialogRef: MatDialogRef<DefaultOtherSettingsComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Pieza
  ) { 
    this.pieza = Object.assign({}, data);
   }

  ngOnInit(): void {
  }

  onNoClick(): void {
    this.dialogRef.close();
  }
  onEditClick(): void {
    this.dialogRef.close(this.pieza);
  }
}
