import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, switchMap } from 'rxjs/operators';
import { TokenService } from './token.service';
import { LoginService } from './login/login.service';
@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor(private tokenService: TokenService, private loginService: LoginService) { }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    return next.handle(request).pipe(
      catchError((error: HttpErrorResponse) => {
        if (error.status === 401) {
          return this.tokenService.refreshToken().pipe(
            switchMap(() => {
              const authToken = localStorage.getItem('authToken') ?? '';
              const clonedRequest = request.clone({ setHeaders: { Authorization: authToken } });
              return next.handle(clonedRequest);
            }),
            catchError((refreshError: HttpErrorResponse) => {
              if (refreshError.status === 400) {
                this.loginService.logout();
              }
              return throwError(refreshError);
            })
          );
        }
        return throwError(error);
      })
    );
  }
}