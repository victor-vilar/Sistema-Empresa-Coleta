import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AppRoutingModule } from 'src/app/app-routing.module';
import { CustomerRoutingModule } from './customer-routing.module';
import { ContractRoutingModule } from '../../contracts/modules/contract-routing.module';
import { MaterialModuleModule } from '../../shared/material-module.module';
import { SharedModule } from '../../shared/shared.module';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RouterModule } from '@angular/router';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';


//customer
import { CustomerMainComponent } from '../components/main/customer-main.component';
import { CustomerListComponent } from '../components/list/customer-list.component';
import { CustomerDetailComponent } from '../components/detail/customer-detail.component';

//addresses
import { CustomerAddressesMainComponent } from '../components/addresses/main/customer-addresses-main.component';
import { CustomerAddressesListComponent } from '../components/addresses/list/customer-addresses-list.component';
import { CustomerAddressesDetailComponent } from '../components/addresses/detail/customer-addresses-detail.component';

//supervisors
import { CustomerSupervisorsMainComponent } from '../components/supervisors/main/customer-supervisors-main.component';
import { CustomerSupervisorsListComponent } from '../components/supervisors/list/customer-supervisors-list.component';
import { CustomerSupervisorsDetailComponent } from '../components/supervisors/detail/customer-supervisors-detail.component';

//contracts
import { CustomerContractsMainComponent } from '../components/contracts/main/customer-contracts-main.component';
import { CustomerContractsListComponent } from '../components/contracts/list/customer-contracts-list.component';
import { CustomerContractsDetailComponent } from '../components/contracts/detail/customer-contracts-detail.component';


import { ItensContractsDetailComponent } from '../components/contracts/util/detail-itens-contract/itens-contracts-detail.component';
import { ItemContractListComponent } from '../components/contracts/util/item-contract-list/item-contract-list.component';

import { CustomerInfoComponent } from '../components/util/customer-info/customer-info.component';





@NgModule({
  declarations: [

    //customer
    CustomerMainComponent,
    CustomerListComponent,
    CustomerDetailComponent,

    //address
    CustomerAddressesMainComponent,
    CustomerAddressesListComponent,
    CustomerAddressesDetailComponent,

    //supervisor
    CustomerSupervisorsMainComponent,
    CustomerSupervisorsListComponent,
    CustomerSupervisorsDetailComponent,

    //customer contracts
    CustomerContractsMainComponent,
    CustomerContractsListComponent,
    CustomerContractsDetailComponent,

    //customer contracts itens
    ItensContractsDetailComponent,
    ItemContractListComponent,

    //util
    CustomerInfoComponent



  ],
  imports: [
    CustomerRoutingModule,
    ContractRoutingModule,
    CommonModule,
    BrowserModule,
    AppRoutingModule,
    NgbModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MaterialModuleModule,
    RouterModule,
    SharedModule,

  ]
})
export class CustomerModule { }
