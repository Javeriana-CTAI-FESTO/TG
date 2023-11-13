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
@Component({
  selector: 'app-widget-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.css']
})
export class CardComponent implements OnInit {
  rol: string = '';
  isloading: boolean = false;
  @Output() dataChanged = new EventEmitter<string>();

  cards: { id: number, idworkPlan: number, title: string, OrderNumber: string, image: string}[] = [];
  filteredCards: { id: number, idworkPlan: number, title: string, OrderNumber: string, image: string}[] = [];
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
    this.isloading = true;
    const authToken = localStorage.getItem('authToken') ?? '';
    const username = this.loginService.getUsername();
    this.dashboardService.getCedulaByUsername(username, authToken).subscribe((cedulaResponse: any) => {
      const cedula = cedulaResponse.cedula;
      this.dashboardService.getOrders(cedula, authToken).subscribe((orders: Object) => {
        const ordersArray = orders as any[];
        ordersArray.forEach((order, index) => {
          this.cards.push({
            id: order.id_part,
            idworkPlan: order.id_workPlan,
            title: order.title,
            OrderNumber: order.orderNumber,
            image: order.image_filePath
          });
        });
        this.filteredCards = this.cards;
        this.isloading = false;
      });
    });
    this.rol=this.loginService.getRole();
   
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
        this.filteredCards = this.cards;
        this.toastr.success(`Se agregó el workplan "${cards[cards.length - 1].title}" un total de ${cards.length} veces a la producción`);
        this.cards=[];
        this.ngOnInit();
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