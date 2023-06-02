import { Component, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { PersonasService, Persona } from '../../personas.service';


@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.css']
})
export class FormComponent implements OnInit {
  

  constructor(public dialogRef: MatDialogRef<FormComponent>, private personasService: PersonasService) { }

  ngOnInit(): void {
  }

  onSubmit(nombre: string, apellido: string, edad: number, ID: string) {
    let persona = new Persona(nombre, apellido, edad, ID);
    if (persona.esValido()) {
      this.personasService.agregarPersona(persona);
      console.log('Persona agregada');
    } else {
      console.log('Persona no agregada');
    }
  }

  closeDialog() {
    this.dialogRef.close();
  }

}