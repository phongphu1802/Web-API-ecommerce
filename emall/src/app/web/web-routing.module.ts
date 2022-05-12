import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { WebComponent } from './web.component';
import { LoginComponent } from './login/login.component';
import { MyaccountComponent } from './myaccount/myaccount.component';
import { HomeComponent } from './home/home.component';
import { TypeproductComponent } from './typeproduct/typeproduct.component'
import { BlogComponent } from './blog/blog.component';
import { ContactComponent } from './contact/contact.component';
import { CartComponent } from './cart/cart.component';
import { ProductDetailComponent } from './product-detail/product-detail.component';
import { HeaderComponent } from './header/header.component';

const routes: Routes = [
  {
    path:'',
    component:WebComponent,
    children:[
      {
        path: '',
        redirectTo: 'home',
        pathMatch:'full',    
      },
      {
        path : "index",component:HomeComponent, 
      },
      {
        path : "home",component:HomeComponent, 
      } ,
      {
        path : "shop",component:HomeComponent, 
      } ,
      {
        path : "login", component:LoginComponent ,
        pathMatch:'full',
      },
      {
        path : "registar", component:LoginComponent ,
        pathMatch:'full',
      },
      {
        path : "myaccount", component:MyaccountComponent,
      },
      {
        path: 'typeproduct',
        children:[
          {
            path: 'Quần',
            component:TypeproductComponent, data:{text:"Quần"}
          },
          {
            path: 'Áo',
            component:TypeproductComponent, data:{text:"Áo"}
          },
          {
            path: 'Giày',
            component:TypeproductComponent, data:{text:"Giày"}
          },
          {
            path: 'Phụ kiện',
            component:TypeproductComponent, data:{text:"Phụ kiện"}
          }
        ]
      },
      {
        path:'blog', component:BlogComponent
      },
      {
        path:'contact', component:ContactComponent
      },
      {
        path:'cart', component:CartComponent
      },
      {
        path:'product-detail',component:ProductDetailComponent
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class WebRoutingModule { }
