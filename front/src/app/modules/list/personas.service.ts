import { Injectable } from '@angular/core';
import { Subject, Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class PersonasService {
  urlBase = 'http://172.21.26.53:8080/api/admin/clients';
  personas: Persona[] = [];
  personaAgregada = new Subject<Persona>();

  constructor(private http: HttpClient) { }

  eliminarPersona(index: number) {
    this.personas.splice(index, 1);
  }

  agregarPersona(persona: Persona) {
    return this.http.post<Persona>(this.urlBase, persona);
  }

  getPersonas(): Persona[] {
    return this.personas;
  }
  getPersonasPorDefecto(): Observable<Persona[]> {
    return this.http.get<Persona[]>(this.urlBase);
  }

  editarPersona(personaOriginal: Persona, personaNueva: Persona): void {
    const index = this.personas.findIndex(p => p.clientNumber === personaOriginal.clientNumber);
    if (index !== -1) {
      this.personas[index] = personaNueva;
    }
  }
}

export interface Persona {
  clientNumber: number;
  firstName: string;
  lastName: string;
  address: string;
  phone: string;
  email: string;
  company: string;
}

