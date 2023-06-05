import { Component, Inject,OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Pieza } from '../../piezas-service.service';
@Component({
  selector: 'app-default-settings',
  templateUrl: './default-settings.component.html',
  styleUrls: ['./default-settings.component.css']
})
export class DefaultSettingsComponent implements OnInit {

  pieza: Pieza;
  constructor(
    public dialogRef: MatDialogRef<DefaultSettingsComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Pieza
  ) { 
    this.pieza = Object.assign({}, data); // create a copy of the object
  }
  

  ngOnInit(): void {
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  onEditClick(): void {
    this.dialogRef.close(this.pieza); // return the copy of the object with the changes
  }

}

