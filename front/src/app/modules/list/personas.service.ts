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
    this.personas.push({ nombre: 'Juan', apellido: 'Pérez', edad: 25, ID: '12345678', address: 'Calle 1', phone: '12345678', email: 'juan@juan.com', company: 'Compania Juan' });
    this.personas.push({ nombre: 'María', apellido: 'González', edad: 30, ID: '87654321', address: 'Calle 2', phone: '87654321', email: 'maria@maria.com', company: 'Compania Maria' });
    this.personas.push({ nombre: 'Pedro', apellido: 'Rodríguez', edad: 40, ID: '45678901', address: 'Calle 3', phone: '45678901', email: 'pedro@pedro',company: 'Compania Pedro' });
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
  address: string;
  phone: string;
  email: string;
  company: string;
}

