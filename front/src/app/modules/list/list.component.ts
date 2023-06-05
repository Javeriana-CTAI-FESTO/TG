import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { FormComponent } from './Dialogs/form/form.component';
import { MatDialog } from '@angular/material/dialog';
import { Subscription } from 'rxjs';
import { PersonasService, Persona } from '../list/personas.service';
import { ToastrService } from 'ngx-toastr';
import { EditComponent } from './Dialogs/edit/edit.component';
import { PersonaDialogComponent } from './Dialogs/persona-dialog/persona-dialog.component';
@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.css']
})
export class ListComponent implements OnInit {
  
  personas: Persona[] = [];
  dataSource: MatTableDataSource<Persona>;

  displayedColumns: string[] = ['nombre', 'apellido', 'edad', 'ID', 'Operaciones'];

  @ViewChild(MatPaginator, { static: true }) paginator!: MatPaginator;
  personaAgregadaSubscription: Subscription;

  constructor(private dialog: MatDialog, private personasService: PersonasService, private toastr: ToastrService) {
    this.dataSource = new MatTableDataSource<Persona>(this.personas);
    this.personaAgregadaSubscription = this.personasService.personaAgregada.subscribe((persona: Persona) => {
      this.dataSource.data = this.personasService.getPersonas();
    });
  }

  ngOnInit(): void {
    this.dataSource.paginator = this.paginator;
    this.personasService.getPersonasPorDefecto();
    this.personas = this.personasService.getPersonas();
    this.dataSource.data = this.personas;
  }
  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  
  openDialog() {
    const dialogRef = this.dialog.open(FormComponent);
  
    dialogRef.afterClosed().subscribe((result: any) => {
      console.log(`Dialog result: ${result}`);
    });
  }
  openInfoDialog(persona: Persona){
    const dialogRef = this.dialog.open(PersonaDialogComponent, {
      data: persona
    });
  
    dialogRef.afterClosed().subscribe((result: any) => {
      console.log(`Dialog result: ${result}`);
    });

  }

  openEditDialog(persona: Persona) {
    const personaCopy = Object.assign({}, persona); // create a copy of the object
    const dialogRef = this.dialog.open(EditComponent, {
      data: personaCopy // pass the copy to the EditComponent
    });
  
    dialogRef.afterClosed().subscribe((result: any) => {
      if (result) {
        this.toastr.success('Persona editada', 'Éxito');
        this.personasService.editarPersona(persona, result); // update the original object with the changes
        this.dataSource.data = this.personasService.getPersonas();
      }
    });
  }

  eliminarPersona(index: string) {
    if (!confirm('¿Está seguro de eliminar esta persona?')) {
      this.toastr.error('Operación Cancelada', 'Error');

      return;
    }
    else{
    }   
    this.personasService.eliminarPersona(index);
    this.toastr.success('Persona eliminada', 'Éxito');
    this.dataSource.data = this.personasService.getPersonas();

  }

  ngOnDestroy() {
    this.personaAgregadaSubscription.unsubscribe();
  }
}