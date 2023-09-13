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
    
    // Verifica si el usuario tiene el rol 'estudiante', 'profesor' o 'administrador'
    if (userRole === 'estudiante') {
      // Si el usuario tiene el rol 'estudiante', solo permite acceso a las rutas '' y 'help'
      if (route.url[0].path === '' || route.url[0].path === 'help') {
        return true;
      } else {
        this.router.navigate(['/']);
        return false;
      }
    } else if (userRole === 'profesor') {
      // Si el usuario tiene el rol 'profesor', permite acceso a todas las rutas excepto a la ruta 'list' y a la ruta 'reports'
      if (route.url[0].path === 'list' || route.url[0].path === 'reports') {
        this.router.navigate(['/']);
        return false;
      } else {
        return true;
      }
    } else if (userRole === 'admin') {
      // Si el usuario tiene el rol 'administrador', permite acceso a todas las rutas
      return true;
    }
    
    // Si el usuario no tiene ninguno de esos roles, redirige al inicio y no permite acceso a la ruta
    this.router.navigate(['/']);
    return false;
  }
}
