import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { VehicleDetailComponent } from '../components/detail/vehicle-detail.component';
import { VehicleListComponent } from '../components/list/vehicle-list.component';
import { VehicleMainComponent } from '../components/main/vehicle-main.component';
import { VehicleRoutingModule } from './vehicle-routing.module';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RouterModule } from '@angular/router';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { AppRoutingModule } from 'src/app/app-routing.module';
import { MaterialModuleModule } from 'src/app/shared/material-module.module';
import { SharedModule } from 'src/app/shared/shared.module';



@NgModule({
  declarations: [
    VehicleMainComponent,
    VehicleListComponent,
    VehicleDetailComponent
  ],
  imports: [
    CommonModule,
    VehicleRoutingModule,
    BrowserModule,
    AppRoutingModule,
    NgbModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MaterialModuleModule,
    RouterModule,
    SharedModule,
  ]
})
export class VehicleModule { }
