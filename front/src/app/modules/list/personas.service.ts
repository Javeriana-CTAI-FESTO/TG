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
    this.personas.push({ nombre: 'Juan', apellido: 'Pérez', edad: 25, ID: '12345678' });
    this.personas.push({ nombre: 'María', apellido: 'González', edad: 30, ID: '87654321' });
    this.personas.push({ nombre: 'Pedro', apellido: 'Rodríguez', edad: 40, ID: '45678901' });
  }

  editarPersona(personaOriginal: Persona, personaNueva: Persona): void {
    const index = this.personas.findIndex(p => p.ID === personaOriginal.ID);
    if (index !== -1) {
      this.personas[index] = personaNueva;
    }
  }
}

export interface Persona {
  nombre: string;
  apellido: string;
  edad: number;
  ID: string;
}

