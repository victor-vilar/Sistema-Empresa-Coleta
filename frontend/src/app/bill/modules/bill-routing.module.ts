import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { BillMainComponent } from '../components/main/bill-main.component';
import { BillDetailComponent } from '../components/detail/bill-detail.component';

const routes: Routes = [
  {path:'contas',component:BillMainComponent, children:[
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
