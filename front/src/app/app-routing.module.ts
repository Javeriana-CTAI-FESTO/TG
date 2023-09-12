import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DefaultComponent } from './layouts/default/default.component';
import { DashboardComponent } from './modules/dashboard/dashboard.component';
import { PostsComponent } from './modules/posts/posts.component';
import { HelpComponent } from './modules/help/help.component';
import { ListComponent } from './modules/list/list.component';
import { LoginComponent } from './login/login.component';
import { PartsComponent } from './modules/parts/parts.component';
import { ResourcesComponent } from './modules/resources/resources.component';
import { AuthGuard } from './guards/auth.guard';
import { RoleGuard } from './guards/role.guard';
import { ReportsComponent } from './modules/reports/reports.component';
const routes: Routes = [{
  path: '',
  component: DefaultComponent,
  canActivate: [AuthGuard], 
  children: [{
    path: '',
    component: DashboardComponent

  }, {
    path: 'posts',
    component: PostsComponent,
    canActivate: [RoleGuard]
  }, {
    path: 'help',
    component: HelpComponent,
    canActivate: [RoleGuard]
  }, {
    path: 'list',
    component: ListComponent,
    canActivate: [RoleGuard]
  }, {
    path: 'parts',
    component: PartsComponent,
    canActivate: [RoleGuard]
  },{
    path: 'resources',
    component: ResourcesComponent,
    canActivate: [RoleGuard]
  },{
    path: 'reports',
    component: ReportsComponent
   // canActivate: [RoleGuard]
  }],
}, {
  path: 'login',
  component: LoginComponent,
}];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
