import { Component, OnInit } from '@angular/core';
import { WorkplanServiceService, Workplan } from 'src/app/modules/posts/workplan-service.service';
import { MatDialog } from '@angular/material/dialog';
import { WorkPlanProgressDialogComponent } from './Dialogs/work-plan-progress-dialog/work-plan-progress-dialog.component';
import { AddWorkPlanToProductionComponent } from './Dialogs/add-work-plan-to-production/add-work-plan-to-production.component';
import { Card } from 'src/app/modules/dashboard.service';
import { ToastrService } from 'ngx-toastr';
@Component({
  selector: 'app-widget-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.css']
})
export class CardComponent implements OnInit {

  cards: { id: number, title: string, state: string, imageUrl: string }[] = [];

  constructor(private workplanService: WorkplanServiceService, private dialog: MatDialog, private toastr: ToastrService) { }

  ngOnInit() {
    this.workplanService.getWorkPlansPorDefecto(); 
    const workplans = this.workplanService.getWorkplans(); 
    const indices = [0, 1, 3, 4, 5, 6, 7, 8, 9];
    this.cards = workplans.filter((workplan, index) => indices.includes(index)).map((workplan, index) => { 
      return {
        id: workplan.id,
        title: workplan.name,
        state: index === 0 ? 'Going' : 'Incomplete',
        imageUrl: '../../../../assets/alexandre-debieve-FO7JIlwjOtU-unsplash.jpg'
      };
    });
  }

  openAddDialog() {
    const dialogRef = this.dialog.open(AddWorkPlanToProductionComponent, {
      width: '250px',
      data: {}
    });
  
    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        const cards = result as Card[];
        cards.forEach(card => {
          this.cards.push({
            id: card.id,
            title: card.title,
            state: 'Incomplete',
            imageUrl: '../../../../assets/alexandre-debieve-FO7JIlwjOtU-unsplash.jpg'
          });
        });
        this.toastr.success(`Se agregó el workplan "${cards[cards.length - 1].title}" un total de ${cards.length} veces a la producción`);
      }
    });
  }

  openProgressDialog() {
    const firstCard = this.cards[0];
    const firstWorkplan = this.workplanService.getWorkplans().find(workplan => workplan.id === firstCard.id);
    if (!firstWorkplan) {
      console.error('No se encontró ningún workplan con el id correspondiente al primer card');
      return;
    }
    const firstSteps = firstWorkplan.steps;
    
    const dialogRef = this.dialog.open(WorkPlanProgressDialogComponent, {
      data: { steps: firstSteps }
    });
  
    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
    });
  }
}