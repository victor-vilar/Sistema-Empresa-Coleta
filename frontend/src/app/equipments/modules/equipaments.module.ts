import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { EquipamentsMainComponent } from '../components/main/equipaments-main.component';
import { EquipmentDetailComponent } from '../components/detail/equipament-detail.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RouterModule } from '@angular/router';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { AppRoutingModule } from 'src/app/app-routing.module';
import { SharedModule } from '../../shared/shared.module';
import { EquipmentRoutingModule } from './equipment-routing.module';
import { MaterialModuleModule } from '../../shared/material-module.module';
import { EquipmentListComponent } from '../components/list/equipment-list.component';



@NgModule({
  declarations: [
    EquipamentsMainComponent,
    EquipmentDetailComponent,
    EquipmentListComponent
  ],
  imports: [
    CommonModule,
    EquipmentRoutingModule,
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
export class EquipamentsModule { }
