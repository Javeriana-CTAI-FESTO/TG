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
    setInterval(() => this.refreshToken().subscribe(), 30000);
  }

  refreshToken() {
    const authToken = localStorage.getItem('authToken') ?? '';
    const formData = new FormData();
    formData.append('Authorization', authToken);
    return this.http.post(environment.urlBaseSecurity+'auth/refresh-token', formData)
      .pipe(tap((res: any) => {
        localStorage.setItem('authToken', res.token);
      }));
}
}