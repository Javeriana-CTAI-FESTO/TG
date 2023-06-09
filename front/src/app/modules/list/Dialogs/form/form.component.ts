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
  
  onSubmit(nombre: string, apellido: string, edad: number, ID: string, address: string, phone: string, email: string, company: string) {
    let persona: Persona = { nombre, apellido, edad, ID, address, phone, email,company };
    if (esValido(persona)) {
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

function esValido(persona: Persona): boolean {
  return persona.nombre.length > 0 && persona.apellido.length > 0 && persona.edad > 0 && persona.ID.length > 0;
}