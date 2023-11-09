import { Component, OnInit, Output, EventEmitter, ViewChild, ElementRef } from '@angular/core';
import { LoginService } from 'src/app/login/login.service';
import { DashboardService } from 'src/app/modules/dashboard.service';
import { ToastrService } from 'ngx-toastr';
import { MatDialog } from '@angular/material/dialog';
import { tap } from 'rxjs/operators';

import { DialogRutasComponent } from 'src/app/modules/dashboard/Dialogs/dialog-rutas/dialog-rutas.component';
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

  constructor(private loginService: LoginService,
    private dashboardsService: DashboardService,
    private toastr: ToastrService,
    private dialog: MatDialog) { }

  toggleSideBar() {
    this.toggleSideBarForMe.emit();
    setTimeout(() => {
      window.dispatchEvent(new Event('resize'));
    }, 300);
  }

  onLogout() {
    this.loginService.logout();
  }
}