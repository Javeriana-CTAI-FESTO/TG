import { Component, OnInit, Output, EventEmitter, ViewChild, ElementRef } from '@angular/core';
import { LoginService } from 'src/app/login/login.service';
import { DashboardService } from 'src/app/modules/dashboard.service';
import { ToastrService } from 'ngx-toastr';
import { MatDialog } from '@angular/material/dialog';
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

  openPrompt() {/*
    const promptString1 = prompt('Ruta Base de Datos:');
    if (promptString1 !== null) {
      this.string1 = promptString1;
    }
    const promptString2 = prompt('Ruta Modulo JAR:');
    if (promptString2 !== null) {
      this.string2 = promptString2;
    }
    this.onSubmit();*/
    const dialogRef = this.dialog.open(DialogRutasComponent, {
      width: '250px',
      data: { string1: this.string1, string2: this.string2 }
    });
  
    dialogRef.afterClosed().subscribe(result => {
      if (result !== undefined) {
        this.string1 = result.string1;
        this.string2 = result.string2;
        this.onSubmit();
      }
    });
  }

  onSubmit() {
    this.dashboardsService.postDbRoute(this.string1, this.string2).subscribe();
    this.toastr.success('Rutas guardadas con exito', 'Rutas');
    localStorage.setItem('dbRoute', this.string1);
    localStorage.setItem('rutaModuloJar', this.string2);
  }
}