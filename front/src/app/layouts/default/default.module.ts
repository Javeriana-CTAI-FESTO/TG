import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DefaultComponent } from './default.component';
import { DashboardComponent } from 'src/app/modules/dashboard/dashboard.component';
import { RouterModule } from '@angular/router';
import { PostsComponent } from 'src/app/modules/posts/posts.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { MatSidenavModule } from '@angular/material/sidenav';
import {MatDividerModule } from '@angular/material/divider';
import { FlexLayoutModule } from '@angular/flex-layout';
import { MatCardModule } from '@angular/material/card';
import { DashboardService } from 'src/app/modules/dashboard.service';
import { HelpComponent } from 'src/app/modules/help/help.component';
import { ListComponent } from 'src/app/modules/list/list.component';
import { MatIconModule } from '@angular/material/icon';
import { DragDropModule } from '@angular/cdk/drag-drop';
import { MatMenuModule } from '@angular/material/menu';
import { PartsComponent } from 'src/app/modules/parts/parts.component';
import { FormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { ResourcesComponent } from 'src/app/modules/resources/resources.component';


@NgModule({
  declarations: [
    DefaultComponent,
    DashboardComponent,
    PostsComponent,
    HelpComponent,
    ListComponent,
    PartsComponent,
    ResourcesComponent
  ],
  imports: [
    CommonModule,
    RouterModule,
    SharedModule,
    MatSidenavModule,
    MatDividerModule,
    FlexLayoutModule,
    MatCardModule,
    MatIconModule,
    DragDropModule,
    MatMenuModule,
    FormsModule,
    MatButtonModule
  ],
  providers: [
    DashboardService
  ],
})
export class DefaultModule { }

