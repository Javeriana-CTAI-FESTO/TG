import { Component, Inject} from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Pieza } from '../../piezas-service.service';
@Component({
  selector: 'app-default-settings',
  templateUrl: './default-settings.component.html',
  styleUrls: ['./default-settings.component.css']
})
export class DefaultSettingsComponent {

  constructor(
    public dialogRef: MatDialogRef<DefaultSettingsComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Pieza) { 
  
  }
  
  onNoClick(): void {
    this.dialogRef.close();
  }


}

