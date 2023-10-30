import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, Router } from '@angular/router';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RoleGuard implements CanActivate {
  constructor(private router: Router) {}

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    // Obtén el rol del usuario desde el local storage o desde un servicio
    const userRole = localStorage.getItem('userRole');
    // Verifica si el usuario tiene el rol 'estudiante', 'profesor', 'administrador' o 'comprador'
    if (userRole === 'STUDENT') {
      // Si el usuario tiene el rol 'estudiante', solo permite acceso a las rutas '' y 'help'
      if (route.url[0].path === '' || route.url[0].path === 'help') {
        return true;
      } else {
        this.router.navigate(['/']);
        return false;
      }
    } else if (userRole === 'TEACHER') {
      // Si el usuario tiene el rol 'profesor', permite acceso a todas las rutas excepto a la ruta 'list', a la ruta 'reports', a la ruta 'create' y a la ruta 'buy'
      if (route.url[0].path === 'list' || route.url[0].path === 'reports' || route.url[0].path === 'create' || route.url[0].path === 'buy') {
        this.router.navigate(['/']);
        return false;
      } else {
        return true;
      }
    } else if (userRole === 'ADMIN') {
      // Si el usuario tiene el rol 'administrador', permite acceso a todas las rutas excepto a la ruta 'buy'
      if (route.url[0].path === 'buy') {
        this.router.navigate(['/']);
        return false;
      } else {
        return true;
      }
    } else if (userRole === 'SHOPPER') {
      // Si el usuario tiene el rol 'comprador' y está intentando acceder a la ruta 'buy', permite el acceso
      if (route.url[0].path === 'buy') {
        return true;
      } else {
        this.router.navigate(['/']);
        return false;
      }
    }
    
    // Si el usuario no tiene ninguno de esos roles, redirige al inicio y no permite acceso a la ruta
    this.router.navigate(['/']);
    return false;
  }
}