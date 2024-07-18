import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';


import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import {MAT_DATE_LOCALE} from '@angular/material/core';
import { RouterModule } from '@angular/router';
import { TemplateModule } from './template/template.module';
import { ResidueModule } from './residue/modules/residue.module';
import { EquipamentsModule } from './equipaments/modules/equipaments.module';
import { CustomerModule } from './customer/modules/customer.module';
import { SharedModule } from './shared/shared.module';
import { LoginModule } from './login/modules/login.module';
import { jwtTokenInterceptorFilter } from './security/jwtTokenInterceptorFilter';
import { csrfTokenInterceptorFilter } from './security/csrfTokenInterceptorFilter';
import { ContractModule } from './contracts/contract.module';
import { MatDialogModule, MatDialogRef, MatDialog, MAT_DIALOG_DATA  } from '@angular/material/dialog';
import { ServiceorderModule } from './service-order/serviceorder.module';
import { BillModule } from './bill/modules/bill.module';




@NgModule({
  declarations: [
    AppComponent,

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgbModule,
    HttpClientModule,
    RouterModule,
    SharedModule,
    TemplateModule,
    ResidueModule,
    EquipamentsModule,
    CustomerModule,
    LoginModule,
    ContractModule,
    ServiceorderModule,
    BillModule

  ],
  providers: [
    {provide: MAT_DATE_LOCALE, useValue: 'pt-BR'},
    {provide: HTTP_INTERCEPTORS, useClass:jwtTokenInterceptorFilter, multi:true},
    {provide: HTTP_INTERCEPTORS, useClass:csrfTokenInterceptorFilter, multi:true},
    {provide: MatDialogRef,useValue: {}},
    {provide: MAT_DIALOG_DATA, useValue: {} }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
