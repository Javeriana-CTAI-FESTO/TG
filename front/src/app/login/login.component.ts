import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService, RegisterData } from './login.service';
import { ToastrService } from 'ngx-toastr';
import { mergeMap } from 'rxjs/operators';

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
    identification: null,
    phone: null,
    rol: ''
  };
  constructor(private loginService: LoginService, private router: Router, private toastr: ToastrService) { }

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

    this.loginService.register(this.registerData).subscribe(response => {
      this.toastr.success(response.message);
      this.showRegisterForm = false;
    }, error => {
      this.toastr.error(error.error.message);
    });
    this.registerData = {
      username: '',
      password: '',
      name: '',
      lastName: '',
      email: '',
      identification: null,
      phone: null,
      rol: ''
    };
  }

  toggleRegisterForm() {
    this.showRegisterForm = !this.showRegisterForm;
  }
}
