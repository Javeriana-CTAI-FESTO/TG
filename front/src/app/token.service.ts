import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class TokenService {

  constructor(private http: HttpClient) { }

  startTokenRefresh() {
    setInterval(() => this.refreshToken().subscribe(), 120000); // 120000 ms = 2 minutes
  }

  refreshToken() {
    const authToken = localStorage.getItem('authToken') ?? '';
    const headers = { 'Authorization': authToken };
    return this.http.post('http://localhost:8081/api/auth/refresh-token', {}, { headers })
      .pipe(tap((res: any) => {
        localStorage.setItem('authToken', res.newToken);
      }));
  }
}