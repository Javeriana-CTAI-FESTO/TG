import { Component, OnInit } from '@angular/core';
import { LoginService } from 'src/app/login/login.service';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit {
  username: string = '';

  constructor(private loginService: LoginService) {}

  ngOnInit(): void {
    this.username = this.loginService.getUsername();
  }
}
