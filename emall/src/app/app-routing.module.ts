import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { WebComponent } from './web/web.component';

const routes: Routes = [
  {
    path:'',
    redirectTo:'web/index',
    pathMatch:'full', 
  },
  {
    path:'admin',
    redirectTo:'/admin',
    pathMatch:'full'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
