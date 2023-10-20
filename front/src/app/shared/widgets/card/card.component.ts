import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { WorkplanServiceService, Workplan } from 'src/app/modules/posts/workplan-service.service';
import { MatDialog } from '@angular/material/dialog';
import { WorkPlanProgressDialogComponent } from './Dialogs/work-plan-progress-dialog/work-plan-progress-dialog.component';
import { AddWorkPlanToProductionComponent } from './Dialogs/add-work-plan-to-production/add-work-plan-to-production.component';
import { Card } from 'src/app/modules/dashboard.service';
import { ToastrService } from 'ngx-toastr';
import { DashboardService } from 'src/app/modules/dashboard.service';
import { HttpClient } from '@angular/common/http';
import { LoginService } from 'src/app/login/login.service';
import { mergeMap, map } from 'rxjs/operators';
@Component({
  selector: 'app-widget-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.css']
})
export class CardComponent implements OnInit {
  rol: string = '';

  @Output() dataChanged = new EventEmitter<string>();

  cards: { id: number, idworkPlan: number, title: string, OrderNumber: string}[] = [];
  filteredCards: { id: number, idworkPlan: number, title: string, OrderNumber: string}[] = [];
  searchTerm: string = '';

  constructor(
    private workplanService: WorkplanServiceService,
    private dialog: MatDialog,
    private toastr: ToastrService,
    private dashboardService: DashboardService,
    private http: HttpClient,
    private loginService: LoginService
  ) { }

  ngOnInit() {
    const authToken = localStorage.getItem('authToken') ?? '';
    const username = this.loginService.getUsername();
    
    this.dashboardService.getCedulaByUsername(username, authToken).pipe(
      mergeMap((cedulaResponse: any) => {
        const cedula = cedulaResponse.cedula;
        return this.dashboardService.getOrders(cedula, authToken);
      }),
      mergeMap((orders: Object) => {
        const ordersArray = orders as any[];
        return this.dashboardService.getOrdersAdmin().pipe(
          map((adminOrders: any[]) => {
            ordersArray.forEach((order, index) => {
              adminOrders.some(adminOrder => {
                if (adminOrder.orderNumber === order.orderNumber  ) {
                  this.cards.push({
                    id: order.id_part,
                    idworkPlan: order.id_workPlan,
                    title: order.title,
                    OrderNumber: order.orderNumber          
                  });
                }
              });
            });
            this.filteredCards = this.cards;
          })
        );
      })
    ).subscribe();
  
    this.rol = this.loginService.getRole();
  }

  openAddDialog() {
    const dialogRef = this.dialog.open(AddWorkPlanToProductionComponent, {
      width: '250px',
      data: {}
    });
  
    dialogRef.afterClosed().subscribe(result => {
      this.searchTerm = '';
      if (result) {
        const cards = result as Card[];
        const isFirstCard = this.cards.length === 0;
        cards.forEach((card, index) => {
          this.cards.push({
            id: card.id,
            idworkPlan: card.idworkPlan,
            title: card.title,
            OrderNumber: card.OrderNumber
          });
        });
        this.filteredCards = this.cards;
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

  filterCards() {
    this.filteredCards = this.cards.filter(card => 
      card.title.toLowerCase().includes(this.searchTerm.toLowerCase()) ||
      card.id.toString().includes(this.searchTerm) ||
      card.OrderNumber.toString().toLowerCase().includes(this.searchTerm.toLowerCase())
    );
  }
}