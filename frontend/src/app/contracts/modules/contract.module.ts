import { CommonModule } from "@angular/common";
import { HttpClientModule } from "@angular/common/http";
import { NgModule } from "@angular/core";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { BrowserModule } from "@angular/platform-browser";
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { RouterModule } from "@angular/router";
import { NgbModule } from "@ng-bootstrap/ng-bootstrap";
import { AppRoutingModule } from "../../app-routing.module";
import { MaterialModuleModule } from "../../shared/material-module.module";
import { SharedModule } from "../../shared/shared.module";
import { ContractRoutingModule } from "./contract-routing.module";
import { ContractsListComponent } from "../components/list/contracts-list.component";
import { ContractsMainComponent } from "../components/main/contracts-main.component";
import { CustomerModule } from "../../customer/modules/customer.module";

@NgModule({
  declarations: [
    ContractsMainComponent,
    ContractsListComponent,
  ],
  imports: [
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
    CustomerModule

  ]
})
export class ContractModule { }
