import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BillMainComponent } from '../components/main/bill-main.component';
import { BillListComponent } from '../components/list/bill-list.component';
import { BillDetailComponent } from '../components/detail/bill-detail.component';
import { BillRoutingModule } from './bill-routing.module';
import { SharedModule } from 'src/app/shared/shared.module';
import { FormsModule } from '@angular/forms';
import { MaterialModuleModule } from 'src/app/shared/material-module.module';





@NgModule({
  declarations: [
    BillMainComponent,
    BillListComponent,
    BillDetailComponent
  ],
  imports: [
    CommonModule,
    BillRoutingModule,
    SharedModule,
    MaterialModuleModule,
    FormsModule,
  ]
})
export class BillModule { }
