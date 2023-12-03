import { ServiceorderRoutingModule } from './serviceorder-routing.module';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ServiceorderComponent } from './serviceorder.component';



@NgModule({
  declarations: [
    ServiceorderComponent
  ],
  imports: [
    CommonModule,
    ServiceorderRoutingModule
  ]
})
export class ServiceorderModule { }
