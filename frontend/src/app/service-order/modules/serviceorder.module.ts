import { ServiceorderRoutingModule } from './serviceorder-routing.module';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MaterialModuleModule } from '../../shared/material-module.module';
import { SharedModule } from '../../shared/shared.module';
import { FormsModule } from '@angular/forms';
import { ServiceorderListComponent } from '../components/list/serviceorder-list.component';
import { PdfTemplateComponent } from '../components/pdf/pdf-template.component';
import { ServiceorderComponent } from '../components/main/serviceorder.component';
import { ServiceorderDetailComponent } from '../components/detail/serviceorder.detail.component';



@NgModule({
  declarations: [
    ServiceorderComponent,
    ServiceorderDetailComponent,
    ServiceorderListComponent,
    PdfTemplateComponent,


  ],
  imports: [
    CommonModule,
    ServiceorderRoutingModule,
    SharedModule,
    MaterialModuleModule,
    FormsModule,

  ]
})
export class ServiceorderModule { }
