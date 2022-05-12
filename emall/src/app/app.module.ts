import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { WebComponent } from './web/web.component';
import { HeaderComponent } from './web/header/header.component';
import { FooterComponent } from './web/footer/footer.component';
import { LoginComponent } from './web/login/login.component';
import { WebModule } from './web/web.module';
import { WebRoutingModule } from './web/web-routing.module';
import { HttpClientModule } from '@angular/common/http';
import { CommonModule, CurrencyPipe, DatePipe } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AdminComponent } from './admin/admin.component';
import { AdminModule } from './admin/admin.module';
import { AdminRoutingModule } from './admin/admin-routing.module';


@NgModule({
  declarations: [
    AppComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    WebRoutingModule,
    WebModule,
    AdminModule,
    AdminRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
