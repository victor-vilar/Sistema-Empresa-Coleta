import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { ContractsMainComponent } from '../components/main/contracts-main.component';



const routes: Routes = [
  {path:'contratos',component:ContractsMainComponent}


]



@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    RouterModule.forChild(routes)
  ],
  exports:[RouterModule]
})
export class ContractRoutingModule { }
