import { Component, OnInit, ViewChild } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { MatDialog } from '@angular/material/dialog';
import { DefaultSettingsComponent } from './Dialogs/default-settings/default-settings.component';
import { Pieza, PiezasServiceService } from './piezas-service.service';
import { ToastrService } from 'ngx-toastr';
import { DefaultWorkPlanComponent } from './Dialogs/default-work-plan/default-work-plan.component';
import { DefaultMRPComponent } from './Dialogs/default-mrp/default-mrp.component';
import { DefaultOtherSettingsComponent } from './Dialogs/default-other-settings/default-other-settings.component';
@Component({
  selector: 'app-parts',
  templateUrl: './parts.component.html',
  styleUrls: ['./parts.component.css']
})
export class PartsComponent implements OnInit {

  piezas: Pieza[] = [];
  dataSource: MatTableDataSource<Pieza>;
  columnas: string[] = ['imagen', 'nombre', 'ID'];

  selectedRow: any;
  
  
  @ViewChild(MatPaginator, { static: true }) paginator!: MatPaginator;

  constructor(private dialog: MatDialog, private piezasService: PiezasServiceService, private toastr: ToastrService) {
    this.dataSource = new MatTableDataSource<Pieza>([]);
  }

  ngOnInit(): void {
    this.dataSource.paginator = this.paginator;
    this.piezasService.getPiezasPorDefecto();
    this.piezas = this.piezasService.getPiezas();
    this.dataSource.data = this.piezas;

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
    const PiezaCopy = Object.assign({}, pieza);
    const dialogRef = this.dialog.open(DefaultSettingsComponent, {
      data: PiezaCopy
    });
    dialogRef.afterClosed().subscribe((result: any) => {
      if (result) {
        this.toastr.success('Se han guardado los cambios', 'Cambios guardados');
        this.piezasService.editarPieza(pieza, result);
        this.dataSource.data = this.piezasService.getPiezas();
        this.piezaSeleccionada = result;
      }
    });

  }

  openEditDialogWorkPlan(pieza: Pieza) {
    const PiezaCopy = Object.assign({}, pieza);
    const dialogRef = this.dialog.open(DefaultWorkPlanComponent, {
      data: PiezaCopy
    });
    dialogRef.afterClosed().subscribe((result: any) => {
      if (result) {
        this.toastr.success('Se han guardado los cambios', 'Cambios guardados');
        this.piezasService.editarPieza(pieza, result);
        this.dataSource.data = this.piezasService.getPiezas();
        this.piezaSeleccionada = result;
      }
    });

  }

  openEditDialogMRP(pieza: Pieza) {
    const PiezaCopy = Object.assign({}, pieza);
    const dialogRef = this.dialog.open(DefaultMRPComponent, {
      data: PiezaCopy
    });
    dialogRef.afterClosed().subscribe((result: any) => {
      if (result) {
        this.toastr.success('Se han guardado los cambios', 'Cambios guardados');
        this.piezasService.editarPieza(pieza, result);
        this.dataSource.data = this.piezasService.getPiezas();
        this.piezaSeleccionada = result;
      }
    });
  }

  openEditDialogOtherSettings(pieza: Pieza) {
    const PiezaCopy = Object.assign({}, pieza);
    const dialogRef = this.dialog.open(DefaultOtherSettingsComponent, {
      data: PiezaCopy
    });
    dialogRef.afterClosed().subscribe((result: any) => {
      if (result) {
        this.toastr.success('Se han guardado los cambios', 'Cambios guardados');
        this.piezasService.editarPieza(pieza, result);
        this.dataSource.data = this.piezasService.getPiezas();
        this.piezaSeleccionada = result;
      }
    });
  }





}

