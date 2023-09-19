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
  rol: string = '';

  constructor(private loginService: LoginService,
    private dashboardsService: DashboardService,
    private toastr: ToastrService,
    private dialog: MatDialog) { }

  ngOnInit() {
    /*const dbRoute = localStorage.getItem('dbRoute');
    const rutaModuloJar = localStorage.getItem('rutaModuloJar');
    if (dbRoute && rutaModuloJar) {
      this.string1 = dbRoute;
      this.string2 = rutaModuloJar;
      this.onSubmit();
    }*/
    this.rol = this.loginService.getRole();
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
    const dialogRef = this.dialog.open(DialogRutasComponent, {
      width: '250px',
      data: { string1: this.string1, string2: this.string2 }
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result !== undefined) {
        this.string1 = result.string1;
        this.string2 = "3";
        this.onSubmit();
      }
    });
  }

  onSubmit() {
    this.dashboardsService.postDbRoute(this.string1, this.string2).subscribe(res => {

      this.toastr.success('Rutas guardada con exito', 'Rutas');
      localStorage.setItem('dbRoute', this.string1);

    }, err => {
      this.toastr.error('Error al guardar las rutas', 'Rutas');
      console.log(err);
    }
    );
   // localStorage.setItem('rutaModuloJar', this.string2);
  }
}