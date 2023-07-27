import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HeaderComponent } from './components/header/header.component';
import { FooterComponent } from './components/footer/footer.component';
import { SidebarComponent } from './components/sidebar/sidebar.component';
import { MatDividerModule } from '@angular/material/divider';
import { MatToolbarModule } from '@angular/material/toolbar';
import {MatIconModule} from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { FlexLayoutModule } from '@angular/flex-layout';
import { MatMenuModule } from '@angular/material/menu';
import {MatListModule} from '@angular/material/list';
import { RouterModule } from '@angular/router';
import { AreaComponent } from './widgets/area/area.component';
import { HighchartsChartModule } from 'highcharts-angular';
import { CardComponent } from './widgets/card/card.component';
import { PieComponent } from './widgets/pie/pie.component';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatTableModule } from '@angular/material/table';
import {MatDialogModule} from '@angular/material/dialog';
import { FormsModule } from '@angular/forms';
import { MatSelectModule } from '@angular/material/select';
import { MatInputModule } from '@angular/material/input';
import { ToastrModule } from 'ngx-toastr';
import { FormComponent } from '../modules/list/Dialogs/form/form.component';
import { EditComponent } from '../modules/list/Dialogs/edit/edit.component';
import { DefaultSettingsComponent } from 'src/app/modules/parts/Dialogs/default-settings/default-settings.component';
import { PersonaDialogComponent } from '../modules/list/Dialogs/persona-dialog/persona-dialog.component';
import { DefaultWorkPlanComponent } from '../modules/parts/Dialogs/default-work-plan/default-work-plan.component';
import { DefaultMRPComponent } from '../modules/parts/Dialogs/default-mrp/default-mrp.component';
import { DefaultOtherSettingsComponent } from '../modules/parts/Dialogs/default-other-settings/default-other-settings.component';
import { WorkplanDialogComponent } from '../modules/posts/Dialogs/workplan-dialog/workplan-dialog.component';
import { EditWorkplanDialogComponent } from '../modules/posts/Dialogs/edit-workplan-dialog/edit-workplan-dialog.component';
import { DragDropModule } from '@angular/cdk/drag-drop';
import { GanttComponent } from './widgets/gantt/gantt.component';
import { WorkPlanProgressDialogComponent } from './widgets/card/Dialogs/work-plan-progress-dialog/work-plan-progress-dialog.component';
import { AddWorkPlanToProductionComponent } from './widgets/card/Dialogs/add-work-plan-to-production/add-work-plan-to-production.component';

@NgModule({
  declarations: [
    HeaderComponent,
    FooterComponent,
    SidebarComponent,
    AreaComponent,
    CardComponent,
    PieComponent,
    FormComponent,
    EditComponent,
    PersonaDialogComponent,
    DefaultSettingsComponent,
    DefaultWorkPlanComponent,
    DefaultMRPComponent,
    DefaultOtherSettingsComponent,
    WorkplanDialogComponent,
    EditWorkplanDialogComponent,
    GanttComponent,
    WorkPlanProgressDialogComponent,
    AddWorkPlanToProductionComponent,
   

  ],
  imports: [
    CommonModule,
    MatDividerModule,
    MatToolbarModule,
    MatIconModule,
    MatButtonModule,
    FlexLayoutModule,
    MatMenuModule,
    MatListModule,
    RouterModule,
    HighchartsChartModule,
    FormsModule,
    DragDropModule,
    MatSelectModule,
    MatInputModule,
    ToastrModule.forRoot(),

  ],
  exports: [
    HeaderComponent,
    FooterComponent,
    SidebarComponent,
    AreaComponent,
    CardComponent,
    PieComponent,
    MatPaginatorModule,
    MatTableModule,
    MatDialogModule,
    GanttComponent
  ]
})
export class SharedModule { }
