import { Component, EventEmitter, OnInit, Output } from '@angular/core';
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

  @Output() dataChanged = new EventEmitter<string>();


  cards: { id: number,idworkPlan:number, title: string, OrderNumber: string, imageUrl: string }[] = [];

  constructor(private workplanService: WorkplanServiceService, private dialog: MatDialog, private toastr: ToastrService) { }

  ngOnInit() {
   
  }

  openAddDialog() {
    const dialogRef = this.dialog.open(AddWorkPlanToProductionComponent, {
      width: '250px',
      data: {}
    });
  
    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        const cards = result as Card[];
        const isFirstCard = this.cards.length === 0;
        cards.forEach((card, index) => {
          this.cards.push({
            id: card.id,
            idworkPlan: card.idworkPlan,
            title: card.title,
            OrderNumber: card.OrderNumber,
            imageUrl: '../../../../assets/alexandre-debieve-FO7JIlwjOtU-unsplash.jpg'
          });
        });
        this.toastr.success(`Se agregó el workplan "${cards[cards.length - 1].title}" un total de ${cards.length} veces a la producción`);
      }
    });
  }
  

  openProgressDialog(firstWorkplanId: number) {
      this.workplanService.getWorkplanById(firstWorkplanId).subscribe((workplan) => {
      const firstSteps = workplan.steps;
      const dialogRef = this.dialog.open(WorkPlanProgressDialogComponent, {
        data: { steps: firstSteps }
      });
      dialogRef.afterClosed().subscribe(result => {
        console.log('The dialog was closed');
      });
    }, (error) => {
      console.error(`Error getting workplan with id ${firstWorkplanId}: ${error}`);
    });
  }

  sendData(data: string) {
    this.dataChanged.emit(data);
  }
}