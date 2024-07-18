import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from '../../template/dashboard/dashboard.component';
import { ResidueDetailComponent } from '../components/detail/residue-detail.component';
import { ResidueMainComponent } from '../components/main/residue-main.component';



const routes: Routes = [
  {path:'residuos',component:ResidueMainComponent, children:[
    {path:'residuo/:id',component:ResidueDetailComponent},
    {path:'residuo/novo',component:ResidueDetailComponent},
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
export class ResidueRoutingModule { }
