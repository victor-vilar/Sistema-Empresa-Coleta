import { ResidueMainComponent } from '../components/main/residue-main.component';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ResidueDetailComponent } from '../components/detail/residue-detail.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RouterModule } from '@angular/router';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { AppRoutingModule } from 'src/app/app-routing.module';
import { TemplateModule } from 'src/app/template/template.module';
import { ResidueRoutingModule } from './residue-routing.module';
import { MaterialModuleModule } from '../../shared/material-module.module';
import { ResidueListTableComponent } from '../components/list/residue-list-table.component';



@NgModule({
  declarations: [
    ResidueMainComponent,
    ResidueDetailComponent,
    ResidueListTableComponent
  ],
  imports: [
    CommonModule,
    ResidueRoutingModule,
    BrowserModule,
    AppRoutingModule,
    NgbModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    RouterModule,
    SharedModule,
    MaterialModuleModule
  ],
  exports:[
    ResidueMainComponent,
    ResidueDetailComponent,
    ResidueListTableComponent]
})
export class ResidueModule { }
