import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { WebComponent } from './web.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { LoginComponent } from './login/login.component';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { AppComponent } from '../app.component';
import { WebRoutingModule } from './web-routing.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MyaccountComponent } from './myaccount/myaccount.component';
import { HomeComponent } from './home/home.component';
import { TypeproductComponent } from './typeproduct/typeproduct.component';
import { BlogComponent } from './blog/blog.component';
import { ContactComponent } from './contact/contact.component';
import { CartComponent } from './cart/cart.component';
import { ProductDetailComponent } from './product-detail/product-detail.component';

@NgModule({
  declarations: [
    WebComponent,
    HeaderComponent,
    FooterComponent,
    LoginComponent,
    MyaccountComponent,
    HomeComponent,
    TypeproductComponent,
    BlogComponent,
    ContactComponent,
    CartComponent,
    ProductDetailComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    WebRoutingModule,
  ],
  exports:[
    HeaderComponent,
    FooterComponent,
    LoginComponent,
  ],
  bootstrap: [WebComponent]
})
export class WebModule { }
