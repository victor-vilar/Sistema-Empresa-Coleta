import { CustomerService } from 'src/app/customer/services/customer.service';
import { CustomerContractsService } from 'src/app/customer/services/customerContracts.service';
import { ServiceorderService } from './../services/serviceorder.service';
import { Component, OnInit, ViewChild } from '@angular/core';
import { Customer } from 'src/app/shared/entities/Customer';
import { Contract } from 'src/app/shared/entities/Contract';
import { ItemContract } from 'src/app/shared/entities/ItemContract';
import { NgForm } from '@angular/forms';
import { Address } from 'src/app/shared/entities/Address';
import { DialogServiceService } from 'src/app/shared/services/dialog-service.service';
import { MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-create',
  templateUrl: './serviceorder.create.component.html',
  styleUrls: ['./serviceorder.create.component.css']
})
export class ServiceorderCreateComponent implements OnInit {

  customers:  Customer      [] = [];
  contracts:  Contract      [] = [];
  itens:      ItemContract  [] = [];
  addresses:  Address       [] = [];

  @ViewChild('form') form!:NgForm;

  constructor(private serviceOrderService:ServiceorderService,
              private customerService:CustomerService,
              private dialogService:DialogServiceService,
              public dialogRef: MatDialogRef<ServiceorderCreateComponent>) { }

  ngOnInit(): void {

    this.customers = this.customerService.list;

  }

  save():void{
    this.dialogService.openProgressDialog()
    let serviceOrder = this.createObject();
    this.serviceOrderService.save(serviceOrder)
    .subscribe(this.serviceOrderSaveObserver());
  }

  createObject():any{
    return {
        serviceExpectedDate:this.form.value.serviceDate,
        itemContract: Number(this.form.value.item),
        address: Number(this.form.value.address),

    }

  }

  findCustomerInformation(){
    this.itens = [];
    let customer = this.customers.find(c => c.cpfCnpj === this.form.value.customer);

    // get all itens available in contracts
    customer.contracts.forEach(contract => {
        contract.itens.forEach(item => this.itens.push(item));
    });

    // get all address that may have a collection
    this.addresses = customer.addresses.filter(a => a.requiresCollection);
  }

  destroy(): void {
    this.dialogRef.close();
  }

  serviceOrderSaveObserver(){
    return{
      next: (response)=>{
        this.dialogService.closeProgressSpinnerDialog();
        this.dialogService.openSuccessDialogWithoutRedirect('Ordem salva com sucesso');
        this.serviceOrderService.getAll();
      },
      error: (error) =>{
        this.dialogService.closeProgressSpinnerDialog();
        this.dialogService.openErrorDialog(error.message);
      }
    }
  }

}
