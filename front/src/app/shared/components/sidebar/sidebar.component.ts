import { Component, OnInit } from '@angular/core';
import { LoginService } from 'src/app/login/login.service';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit {
  username: string = '';
  rol: string = '';

  constructor(private loginService: LoginService) {}

  ngOnInit(): void {
    this.username = this.loginService.getUsername();
    this.rol = this.loginService.getRole();
  
    const roleMap: Record<string, string> = {
      'admin': 'Administrador',
      'estudiante': 'Estudiante',
      'profesor': 'Profesor',
      'comprador': 'Comprador'
    };
  
    this.rol = roleMap[this.rol] || this.rol;
  }
}
