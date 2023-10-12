import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';

interface LoginResponse {
  token: string;
}

export interface RegisterData {
  username: string;
  password: string;
  name: string;
  lastName: string;
  email: string;
  identification: number;
  phone: number;
  admin: boolean;
  student: boolean;
  teacher: boolean;
  comprador: boolean;
}

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  private urlBase='https://localhost:8443/api/'

  constructor(private http: HttpClient, private router: Router) {}

  login(username: string, password: string): Observable<LoginResponse> {
    const headers = new HttpHeaders({
      'Authorization': 'Basic ' + btoa(username + ':' + password)
    });
    localStorage.setItem('username', username);
    return this.http.post<LoginResponse>(this.urlBase+'auth/login', {}, { headers });
  }

  register(data: RegisterData): Observable<any> {
    return this.http.post(this.urlBase+'auth/register', data);
  }

  logout() {
    localStorage.removeItem('authToken');
    localStorage.removeItem('username');
    localStorage.removeItem('userRole');
    this.router.navigate(['/login']);
  }

  getUsername(): string {
    const username = localStorage.getItem('username');
    return username ?? '';
  }
  getRole(): string {
    const role = localStorage.getItem('userRole');
    return role ?? '';
  }
  obtenerDatos() {
    const token = localStorage.getItem('authToken');
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    const username = localStorage.getItem('username');
    return this.http.get<{ rol: string }>(this.urlBase+'user/get/rol/username=' + username, { headers });
  }
}
