import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AdminComponent } from './admin.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AdminRoutingModule } from './admin-routing.module';
import { HomeComponent } from './home/home.component';
import { ProductComponent } from './product/product.component';
import { OrderComponent } from './order/order.component';
import { AccountComponent } from './account/account.component';

@NgModule({
  declarations: [
    AdminComponent,
    HeaderComponent,
    FooterComponent,
    HomeComponent,
    ProductComponent,
    OrderComponent,
    AccountComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    AdminRoutingModule
  ],
  exports:[
    HeaderComponent,
    FooterComponent,
  ],
  bootstrap: [AdminComponent]
})
export class AdminModule { }
