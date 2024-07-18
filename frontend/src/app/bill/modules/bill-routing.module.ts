import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { ServiceorderDetailComponent } from 'src/app/service-order/service-order-detail/serviceorder.detail.component';
import { ServiceorderComponent } from 'src/app/service-order/serviceorder.component';
import { BillComponent } from '../components/main/bill.component';
import { BillDetailComponent } from '../components/detail/bill-detail.component';

const routes: Routes = [
  {path:'contas',component:BillComponent, children:[
    {path:'novo',component:BillDetailComponent},
    {path:':id',component:BillDetailComponent},
  ]
}]

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    RouterModule.forChild(routes)
  ],
  exports:[
   RouterModule
  ]
})
export class BillRoutingModule { }
