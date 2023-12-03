import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from '../template/dashboard/dashboard.component';
import { ContractModule } from '../contracts/contract.module';
import { CustomerModule } from '../customer/customer.module';
import { ResidueModule } from '../residue/residue.module';
import { ServiceorderComponent } from './serviceorder.component';
import { EquipamentsModule } from '../equipaments/equipaments.module';

const routes: Routes = [
  {path:'ordem-servico',component:ServiceorderComponent, children:[

  ]},
]



@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    ResidueModule,
    EquipamentsModule,
    CustomerModule,
    ContractModule,
    RouterModule.forChild(routes)
  ],
  exports:[RouterModule]
})
export class ServiceorderRoutingModule { }
