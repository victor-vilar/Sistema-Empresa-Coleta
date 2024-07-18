import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { LoginMainComponent } from '../components/main/login-main.component';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { LoginRoutingModule } from './login-routing.module';
import { MaterialModuleModule } from '../../shared/material-module.module';



@NgModule({
  declarations: [
    LoginMainComponent
  ],
  imports: [
    CommonModule,
    LoginRoutingModule,
    FormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    FormsModule,
    MaterialModuleModule
  ],
  exports:[
    LoginMainComponent
  ]
})
export class LoginModule { }
