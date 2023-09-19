import { Component,Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-dialog-rutas',
  templateUrl: './dialog-rutas.component.html',
  styleUrls: ['./dialog-rutas.component.css']
})
export class DialogRutasComponent {
  constructor(
    public dialogRef: MatDialogRef<DialogRutasComponent>,
    @Inject(MAT_DIALOG_DATA) public data: { string1: string, string2: string }
  ) {}

  onNoClick(): void {
    this.dialogRef.close();
  }
  onOkClick(): void {
    this.dialogRef.close(this.data);
  }
}
