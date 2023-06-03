import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Persona } from '../../personas.service';

@Component({
  selector: 'app-edit',
  templateUrl: './edit.component.html',
  styleUrls: ['./edit.component.css']
})
export class EditComponent implements OnInit {

  persona: Persona;

  constructor(
    public dialogRef: MatDialogRef<EditComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Persona
  ) { 
    this.persona = Object.assign({}, data); // create a copy of the object
  }

  ngOnInit(): void {
    // no need to assign the data to persona here
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  onEditClick(): void {
    this.dialogRef.close(this.persona); // return the copy of the object with the changes
  }

}