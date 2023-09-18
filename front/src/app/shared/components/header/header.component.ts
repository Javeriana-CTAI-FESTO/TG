import { Component, OnInit, Output, EventEmitter, ViewChild, ElementRef } from '@angular/core';
import { LoginService } from 'src/app/login/login.service';
import { DashboardService } from 'src/app/modules/dashboard.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent {
  rutaModuloJar: string = "";
  showFileInputs = false;

  @Output() toggleSideBarForMe: EventEmitter<any> = new EventEmitter();
  string1: string = '';
  string2: string = '';

  constructor(private loginService: LoginService, private dashboardsService: DashboardService) { }

  ngOnInit() {
    /*this.dashboardsService.postDbRoute("/home/capitan/Documentos/GitHub/TG/Backend/Festo/FestoMES_be.accdb","/home/capitan/Documentos/GitHub/TG/Backend/Festo/target/tg-0.0.1-SNAPSHOT.jar").subscribe(data => {
      console.log(data);
    },
    error => {
      console.log(error);
    });   */
   }

  toggleSideBar() {
    this.toggleSideBarForMe.emit();
    setTimeout(() => {
      window.dispatchEvent(new Event('resize'));
    }, 300);
  }

  onLogout() {
    this.loginService.logout();
  }

  openPrompt() {
    const promptString1 = prompt('String 1:');
    if (promptString1 !== null) {
      this.string1 = promptString1;
    }
    const promptString2 = prompt('String 2:');
    if (promptString2 !== null) {
      this.string2 = promptString2;
    }
    this.onSubmit();
  }

  onSubmit() {
    this.dashboardsService.postDbRoute(this.string1, this.string2).subscribe(data => {
      console.log(data);
    },
    error => {
      console.log(error);
    }
    );
  }
}