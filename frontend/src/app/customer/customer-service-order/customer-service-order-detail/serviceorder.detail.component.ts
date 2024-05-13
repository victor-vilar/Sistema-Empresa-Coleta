import { CustomerService } from 'src/app/customer/services/customer.service';
import { CustomerContractsService } from 'src/app/customer/services/customerContracts.service';
import { ServiceorderService } from '../services/serviceorder.service';
import { Component, OnInit, ViewChild, inject } from '@angular/core';
import { Customer } from 'src/app/shared/entities/Customer';
import { Contract } from 'src/app/shared/entities/Contract';
import { ItemContract } from 'src/app/shared/entities/ItemContract';
import { NgForm } from '@angular/forms';
import { Address } from 'src/app/shared/entities/Address';
import { DialogServiceService } from 'src/app/shared/services/dialog-service.service';
import { MatDialogRef } from '@angular/material/dialog';
import { ErrorsHelperService } from 'src/app/shared/services/erros-helper.service';
import { ServiceorderDetailComponentErrorsHelperService } from '../services/serviceorder-detail-component-errors-helper.service';
import { FormDetail } from 'src/app/shared/entities/FormDetail';

@Component({
  selector: 'app-create',
  templateUrl: './serviceorder.detail.component.html',
  styleUrls: ['./serviceorder.detail.component.css']
})
export class ServiceorderDetailComponent extends FormDetail implements OnInit {

  @ViewChild('form') form!:NgForm;

  customers:  Customer      [] = [];
  contracts:  Contract      [] = [];
  itens:      ItemContract  [] = [];
  addresses:  Address       [] = [];

  private serviceOrderService:ServiceorderService = inject(ServiceorderService);
  private customerService:CustomerService = inject(CustomerService);
  private errorsHelper:ErrorsHelperService = inject(ServiceorderDetailComponentErrorsHelperService);



  constructor(
              public dialogRef: MatDialogRef<ServiceorderDetailComponent>
            ) { super();}

  ngOnInit(): void {

    this.customers = this.customerService.list;

  }

  save():void{
    this.errorsHelper.checkErrors(this.form);
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
        console.log(error);
      }
    }
  }

}
