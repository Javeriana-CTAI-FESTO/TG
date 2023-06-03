import { Component, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { PersonasService, Persona } from '../../personas.service';
import { ToastrService } from 'ngx-toastr';


@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.css']
})
export class FormComponent implements OnInit {
  

  constructor(public dialogRef: MatDialogRef<FormComponent>, private personasService: PersonasService,private toastr: ToastrService) { }

  ngOnInit(): void {
    
  }
  

  onSubmit(nombre: string, apellido: string, edad: number, ID: string) {
    let persona = new Persona(nombre, apellido, edad, ID);
    if (persona.esValido()) {
      this.personasService.agregarPersona(persona);
      this.toastr.success('Persona agregada', 'Éxito');
    } else {
      this.toastr.error('Persona no agregada', 'Error');
    }
  }

  closeDialog() {
    this.dialogRef.close();
  }

}