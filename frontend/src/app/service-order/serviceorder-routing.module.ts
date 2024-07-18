import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from '../template/dashboard/dashboard.component';
import { ContractModule } from '../contracts/contract.module';
import { CustomerModule } from '../customer/modules/customer.module';
import { ResidueModule } from '../residue/modules/residue.module';
import { ServiceorderComponent } from './serviceorder.component';
import { EquipamentsModule } from '../equipaments/modules/equipaments.module';
import { ServiceorderDetailComponent } from './service-order-detail/serviceorder.detail.component';
import { PdfTemplateComponent } from './pdf-template/pdf-template.component';



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
