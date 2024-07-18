import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { PdfTemplateComponent } from '../components/pdf/pdf-template.component';
import { ServiceorderComponent } from '../components/main/serviceorder.component';
import { ServiceorderDetailComponent } from '../components/detail/serviceorder.detail.component';



const routes: Routes = [
  {path:'ordem-servico',component:ServiceorderComponent, children:[
    {path:'novo',component:ServiceorderDetailComponent},
    {path:':id',component:ServiceorderDetailComponent},
    {path:'pdf',component:PdfTemplateComponent},
    {path:'pdf/:id',component:PdfTemplateComponent}
  ]},
]



@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    RouterModule.forChild(routes)
  ],
  exports:[RouterModule]
})
export class ServiceorderRoutingModule { }
