import { Component, OnInit, ViewChild, AfterViewInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { MatDialog } from '@angular/material/dialog';
import { DefaultSettingsComponent } from './Dialogs/default-settings/default-settings.component';
import { Pieza, PiezasServiceService } from './piezas-service.service';
import { ToastrService } from 'ngx-toastr';
import { DefaultWorkPlanComponent } from './Dialogs/default-work-plan/default-work-plan.component';
import { DefaultMRPComponent } from './Dialogs/default-mrp/default-mrp.component';
import { DefaultOtherSettingsComponent } from './Dialogs/default-other-settings/default-other-settings.component';
import { AddPartComponent } from './Dialogs/add-part/add-part.component';
import { LoginService } from 'src/app/login/login.service';

@Component({
  selector: 'app-parts',
  templateUrl: './parts.component.html',
  styleUrls: ['./parts.component.css']
})
export class PartsComponent implements OnInit {

  piezas: Pieza[] = [];
  dataSource: MatTableDataSource<Pieza>;
  columnas: string[] = ['Picture', 'Description', 'operations'];
  selectedRow: any;
  rol: string = '';
  picturePaths: { name: string, url: string }[] = [];

  @ViewChild(MatPaginator, { static: false }) paginator!: MatPaginator;

  constructor(private loginService: LoginService, private dialog: MatDialog, private piezasService: PiezasServiceService, private toastr: ToastrService) {
    this.dataSource = new MatTableDataSource<Pieza>([]);
  }

  ngOnInit(): void {
    this.piezasService.getPiezasPorDefecto().subscribe(
      (data: { piezas: Pieza[], picturePaths: { name: string, url: string }[] }) => {
        this.piezas = data.piezas;
        this.picturePaths = data.picturePaths;
        this.dataSource.data = this.piezas;
        // Mover la asignación del paginador aquí
        this.dataSource.paginator = this.paginator;
      },
      (error: any) => {
        console.error(error);
      }
    );
    this.rol = this.loginService.getRole();
  }

  

  piezaSeleccionada: Pieza | null = null;

  seleccionarPieza(pieza: Pieza | null): void {
    this.selectedRow = pieza;
    this.piezaSeleccionada = pieza;
  }
  mostrarInfo(pieza: Pieza): void {
    this.seleccionarPieza(pieza);
  }
  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }
  openEditDialogSettings(pieza: Pieza) {
    const dialogRef = this.dialog.open(DefaultSettingsComponent, {
      data: pieza
    });

  }
  openEditDialogWorkPlan(pieza: Pieza) {
    const dialogRef = this.dialog.open(DefaultWorkPlanComponent, {
      data: pieza
    });
  }
  openEditDialogMRP(pieza: Pieza) {
    const dialogRef = this.dialog.open(DefaultMRPComponent, {
      data: pieza
    });

  }
  openEditDialogOtherSettings(pieza: Pieza) {
    const dialogRef = this.dialog.open(DefaultOtherSettingsComponent, {
      data: pieza
    });
  }
  openAddPartDialog() {
    const dialogRef = this.dialog.open(AddPartComponent, {
      data: this.picturePaths
    });

    dialogRef.componentInstance.piezaAdded.subscribe(() => {
      this.ngOnInit();
    });
  }
}