import { ServiceorderRoutingModule } from './serviceorder-routing.module';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ServiceorderComponent } from './serviceorder.component';
import { ServiceorderDetailComponent } from './service-order-detail/serviceorder.detail.component';
import { MaterialModuleModule } from '../shared/material-module.module';
import { SharedModule } from '../shared/shared.module';
import { FormsModule } from '@angular/forms';
import { ServiceorderListComponent } from './serviceorder-list/serviceorder-list.component';
import { jsPDF } from "jspdf";
import { PdfTemplateComponent } from './pdf-template/pdf-template.component';
import { ServiceOrderAddCollectionComponent } from './service-order-add-collection/service-order-add-collection.component';




@NgModule({
  declarations: [
    ServiceorderComponent,
    ServiceorderDetailComponent,
    ServiceorderListComponent,
    PdfTemplateComponent,
    ServiceOrderAddCollectionComponent,

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
