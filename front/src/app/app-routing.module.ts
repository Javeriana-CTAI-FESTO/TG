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

const routes: Routes = [{
  path: '',
  component: DefaultComponent,
  children: [{
    path: '',
    component: DashboardComponent
  }, {
    path: 'posts',
    component: PostsComponent
  }, {
    path: 'help',
    component: HelpComponent
  }, {
    path: 'list',
    component: ListComponent
  }, {
    path: 'parts',
    component: PartsComponent
  },{
    path: 'resources',
    component: ResourcesComponent
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
