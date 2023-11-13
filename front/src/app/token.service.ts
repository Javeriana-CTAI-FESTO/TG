import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { tap } from 'rxjs/operators';
import { environment } from 'src/enviroments/enviroment';
@Injectable({
  providedIn: 'root'
})
export class TokenService {

  constructor(private http: HttpClient) { }

  startTokenRefresh() {
    setInterval(() => this.refreshToken().subscribe(), 120000);
  }

  refreshToken() {
    console.log('refreshing token...');
    const authToken = localStorage.getItem('authToken') ?? '';
    const headers = { 'Authorization': authToken };
    return this.http.post(environment.urlBaseSecurity+'auth/refresh-token', {}, { headers })
      .pipe(tap((res: any) => {
        localStorage.setItem('authToken', res.newToken);
      }));
  }
}