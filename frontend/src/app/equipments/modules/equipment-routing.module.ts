import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { EquipmentDetailComponent } from '../components/detail/equipament-detail.component';
import { EquipmentsComponent } from '../components/main/equipaments.component';


const routes: Routes = [
  {path:'equipamentos',component:EquipmentsComponent, children:[
    {path:'equipamento/:id',component:EquipmentDetailComponent},
    {path:'equipamento/novo',component:EquipmentDetailComponent},

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
export class EquipmentRoutingModule { }
