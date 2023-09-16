import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService, RegisterData } from './login.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  username = '';
  password = '';
  showRegisterForm = false;
  registerData: RegisterData = {
    username: '',
    password: '',
    name: '',
    lastName: '',
    email: '',
    identification: 0,
    phone: 0,
    admin: false,
    student: false,
    teacher: false
  };
  
  constructor(private loginService: LoginService, private router: Router, private toastr: ToastrService) {}

  onSubmit() {
    this.loginService.login(this.username, this.password).subscribe(response => {
      localStorage.setItem('authToken', response.token);
      this.toastr.success('Login Exitoso');
      this.loginService.obtenerDatos().subscribe(response => {
        const rolValue = response.rol;
        localStorage.setItem('userRole', rolValue);
        this.router.navigate(['/']);
      });
    }, error => {
      console.log(error);
      this.toastr.error('Login fallido');
    });
  }
  

  onRegister() {
    this.registerData.admin = false;
    this.registerData.student = true;
    this.registerData.teacher = false;
    this.loginService.register(this.registerData).subscribe(response => {
      this.toastr.success(response.message);
      this.showRegisterForm = false;
    }, error => {
      this.toastr.error(error.error.message);
    });
  }

  toggleRegisterForm() {
    this.showRegisterForm = !this.showRegisterForm;
  }
}
