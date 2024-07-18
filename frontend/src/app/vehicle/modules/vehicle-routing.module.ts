import { Routes } from '@angular/router';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { VehicleMainComponent } from '../components/main/vehicle-main.component';
import { VehicleDetailComponent } from '../components/detail/vehicle-detail.component';

const routes: Routes = [
  {path:'veiculos', component:VehicleMainComponent, children:[
    {path:'novo', component:VehicleDetailComponent},
    {path:':id', component:VehicleDetailComponent}
  ]}

]


@NgModule({
  declarations: [],
  imports: [
    CommonModule
  ]
})
export class VehicleRoutingModule { }
