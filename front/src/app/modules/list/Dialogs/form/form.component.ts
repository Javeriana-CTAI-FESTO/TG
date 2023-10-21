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
  model: any = {};
  constructor(public dialogRef: MatDialogRef<FormComponent>, private personasService: PersonasService,private toastr: ToastrService) { }

  ngOnInit(): void {
    
  }
  
  onSubmit(firstName: string, lastName: string, clientNumber: number, address: string, phone: string, email: string, company: string) {
    let persona: Persona = { firstName, lastName, clientNumber, address, phone, email, company };
    if (esValido(persona)) {
      this.personasService.agregarPersona(persona).subscribe(
        (response: Persona) => {
          this.toastr.success('Persona agregada', 'Exito');
          this.closeDialog();
        },
        (error: any) => {
          console.error(error);
          this.toastr.error('Persona no agregada', 'Error');
        }
      );

    } else {
      this.toastr.error('Persona no agregada', 'Error');
    }
  }

  closeDialog() {
    this.dialogRef.close();
  }

}

function esValido(persona: Persona): boolean {
  return persona.firstName.length > 0 && persona.lastName.length > 0 ;
}