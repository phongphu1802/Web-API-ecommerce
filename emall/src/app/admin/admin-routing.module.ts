import { Component, NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { AdminComponent } from './admin.component';
import { HomeComponent } from './home/home.component';
import { ProductComponent } from './product/product.component';
import { OrderComponent } from './order/order.component';
import { AccountComponent } from './account/account.component';

const routes: Routes = [
  {
    path:'admin',
    component:AdminComponent,
    children:[
      {
        path: '',
        redirectTo: 'index',
        pathMatch:'full',    
      },
      {
        path : "",component:HomeComponent, 
      },
      {
        path : "product",component:ProductComponent, 
      },
      {
        path : "order",component:OrderComponent, 
      },
      {
        path : "account", component:AccountComponent,
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
