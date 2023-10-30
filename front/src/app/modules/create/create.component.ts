import { Component } from '@angular/core';
import { LoginService, RegisterData } from 'src/app/login/login.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-create',
  templateUrl: './create.component.html',
  styleUrls: ['./create.component.css']
})
export class CreateComponent {
  registerData: RegisterData = {
    username: '',
    password: '',
    name: '',
    lastName: '',
    email: '',
    identification: 0,
    phone: 0,
    rol: ''
  };

  constructor(private loginService: LoginService, private toastr: ToastrService) {}

  onRegister() {
    this.loginService.register(this.registerData).subscribe(response => {
      this.toastr.success(response.message);
    }, error => {
      this.toastr.error(error.error.message);
    });  
    this.registerData = {
      username: '',
      password: '',
      name: '',
      lastName: '',
      email: '',
      identification: 0,
      phone: 0,
      rol: ''
    };
  }
}