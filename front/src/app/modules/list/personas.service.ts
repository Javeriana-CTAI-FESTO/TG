import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class PersonasService {
  personas: Persona[] = [];
  personaAgregada = new Subject<Persona>();


  eliminarPersona(index: string) {
 
    this.personas.splice(parseInt(index), 1);
    

  }

  agregarPersona(persona: Persona) {
    this.personas.push(persona);
    this.personaAgregada.next(persona);
  }

  getPersonas(): Persona[] {
    return this.personas;
  }

  getPersonasPorDefecto(): void {
    this.personas.push(new Persona('Juan', 'Pérez', 25, '12345678'));
    this.personas.push(new Persona('María', 'González', 30, '87654321'));
    this.personas.push(new Persona('Pedro', 'Rodríguez', 40, '45678901'));
  }
  editarPersona(personaOriginal: Persona, personaNueva: Persona): void {
    const index = this.personas.findIndex(p => p.ID === personaOriginal.ID);
    if (index !== -1) {
      this.personas[index] = personaNueva;
    }
  }
}

export class Persona {
  nombre: string;
  apellido: string;
  edad: number;
  ID: string;

  constructor(nombre: string, apellido: string, edad: number, ID: string) {
    this.nombre = nombre;
    this.apellido = apellido;
    this.edad = edad;
    this.ID = ID;
  }

  esValido(): boolean {
    return this.nombre.length > 0 && this.apellido.length > 0 && this.edad > 0 && this.ID.length > 0;
  }
  
}