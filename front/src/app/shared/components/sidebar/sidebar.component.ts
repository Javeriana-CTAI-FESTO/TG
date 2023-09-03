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
    if (this.rol === 'admin') {
      this.rol = 'Administrador';
    }
    if (this.rol === 'estudiante') {
      this.rol = 'Estudiante';
    }
    if (this.rol === 'profesor') {
      this.rol = 'Profesor';
    }

  }
}
