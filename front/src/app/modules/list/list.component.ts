import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { FormComponent } from './Dialogs/form/form.component';
import { MatDialog } from '@angular/material/dialog';
import { Subscription } from 'rxjs';
import { PersonasService, Persona } from '../list/personas.service';

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

  constructor(private dialog: MatDialog, private personasService: PersonasService) {
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

  ngOnDestroy() {
    this.personaAgregadaSubscription.unsubscribe();
  }
}