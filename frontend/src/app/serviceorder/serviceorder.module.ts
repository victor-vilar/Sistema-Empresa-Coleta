import { ServiceorderRoutingModule } from './serviceorder-routing.module';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ServiceorderComponent } from './serviceorder.component';
import { ServiceorderCreateComponent } from './create/serviceorder.create.component';
import { MaterialModuleModule } from '../shared/material-module.module';
import { SharedModule } from '../shared/shared.module';
import { FormsModule } from '@angular/forms';
import { ServiceorderListComponent } from './serviceorder-list/serviceorder-list.component';
import { jsPDF } from "jspdf";
import { PdfTemplateComponent } from './pdf-template/pdf-template.component';




@NgModule({
  declarations: [
    ServiceorderComponent,
    ServiceorderCreateComponent,
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
