import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { CustomerAddressesDetailComponent } from '../components/addresses/detail/customer-addresses-detail.component';
import { CustomerAddressesMainComponent } from '../components/addresses/main/customer-addresses-main.component';
import { CustomerContractsDetailComponent } from '../components/contracts/detail/customer-contracts-detail.component';
import { CustomerDetailComponent } from '../components/detail/customer-detail.component';
import { CustomerMainComponent } from '../components/main/customer-main.component';
import { CustomerSupervisorsDetailComponent } from '../components/supervisors/detail/customer-supervisors-detail.component';
import { CustomerSupervisorsMainComponent } from '../components/supervisors/main/customer-supervisors-main.component';
import { CustomerContractsMainComponent } from '../components/contracts/main/customer-contracts-main.component';


const routes: Routes = [
  {path:'clientes',component:CustomerMainComponent, children:[
    {path:'cliente/:id',component:CustomerDetailComponent},
    {path:'cliente/novo',component:CustomerDetailComponent},
  ]},
  {path:'cliente/:cpfCnpj/contratos',component:CustomerContractsMainComponent, children:[
    {path:'contrato/:id',component:CustomerContractsDetailComponent},
    {path:'contrato/novo',component:CustomerContractsDetailComponent},
  ]},
  {path:'cliente/:cpfCnpj/enderecos',component:CustomerAddressesMainComponent, children:[
    {path:'endereco/:id',component:CustomerAddressesDetailComponent},
    {path:'endereco/novo',component:CustomerAddressesDetailComponent},
  ]},
  {path:'cliente/:cpfCnpj/fiscais',component:CustomerSupervisorsMainComponent, children:[
    {path:'fiscal/:id',component:CustomerSupervisorsDetailComponent},
    {path:'fiscal/novo',component:CustomerSupervisorsDetailComponent},
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
export class CustomerRoutingModule { }
