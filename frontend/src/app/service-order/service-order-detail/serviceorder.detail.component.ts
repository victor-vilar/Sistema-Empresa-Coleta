import { CustomerService } from 'src/app/customer/services/customer.service';
import { CustomerContractsService } from 'src/app/customer/services/customerContracts.service';
import { ServiceorderService } from '../services/serviceorder.service';
import { AfterViewInit, Component, Inject, OnInit, ViewChild, inject } from '@angular/core';
import { Customer } from 'src/app/shared/entities/Customer';
import { Contract } from 'src/app/shared/entities/Contract';
import { ItemContract } from 'src/app/shared/entities/ItemContract';
import { NgForm } from '@angular/forms';
import { Address } from 'src/app/shared/entities/Address';
import { DialogServiceService } from 'src/app/shared/services/dialog-service.service';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { ErrorsHelperService } from 'src/app/shared/services/erros-helper.service';
import { ServiceorderDetailComponentErrorsHelperService } from '../services/serviceorder-detail-component-errors-helper.service';
import { FormDetail } from 'src/app/shared/entities/FormDetail';
import { ServiceOrder } from 'src/app/shared/entities/ServiceOrder';

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
  public selectedServiceOrder:ServiceOrder;
  public operation:string = 'Cadastrar Nova Ordem';


  constructor(
    public dialogRef: MatDialogRef<ServiceorderDetailComponent>,
    @Inject(MAT_DIALOG_DATA) public data:any
    ) { super();}

  ngOnInit(): void {
    this.onLoad(this.data);
    this.customers = this.customerService.list;

    if(this.idOfEditedItem !== undefined && this.idOfEditedItem !== null){
      this.selectedServiceOrder = this.serviceOrderService.list.find(os => os.id === this.idOfEditedItem);
      this.operation = 'Atualizar Ordem';
      this.fillForm();
    }

  }

  fillForm(){
    console.log(this.selectedServiceOrder)
    let customer = this.customers.find(c => c.cpfCnpj === this.selectedServiceOrder.customerId);
    this.findCustomerInformation(customer);

    setTimeout(() =>{
      this.form.setValue({
        serviceExpectedDate:this.selectedServiceOrder.serviceExpectedDate,
        customer:customer.cpfCnpj,
        item:String(this.selectedServiceOrder.itemContract.id),
        address:String(this.selectedServiceOrder.address.id),
        observation:this.selectedServiceOrder.observation,
        serviceExecutedDate:this.selectedServiceOrder.serviceExecutedDate,
        serviceTime:this.selectedServiceOrder.serviceTime,
        amountCollected:this.selectedServiceOrder.amountCollected,
        ineaManifest:this.selectedServiceOrder.ineaManifest,
        osFileUrl:this.selectedServiceOrder.osFileUrl
      })
    },20)
  }


  save():void{
    //this.errorsHelper.checkErrors(this.form);
    this.dialogService.openProgressDialog()
    let serviceOrder = this.createObject();

    if(this.selectedServiceOrder !== undefined){
      serviceOrder.id = this.selectedServiceOrder.id;
    }

    console.log(serviceOrder);
    if(serviceOrder.id === undefined){
      this.subscriptionsList.push(this.serviceOrderService.save(serviceOrder)
      .subscribe(this.serviceOrderSaveObserver()));
    }else{
      this.subscriptionsList.push(this.serviceOrderService.update(serviceOrder)
      .subscribe(this.serviceOrderSaveObserver()));
    }

  }

  createObject():any{
    return {
        serviceExpectedDate:this.form.value.serviceExpectedDate,
        itemContract: Number(this.form.value.item),
        address: Number(this.form.value.address),
        observation:this.form.value.observation,
        serviceExecutedDate:this.form.value.serviceExecutedDate,
        serviceTime:this.form.value.serviceTime,
        amountCollected:this.form.value.amountCollected,
        ineaManifest:this.form.value.ineaManifest,
        osFileUrl:this.form.value.osFileUrl
    }

  }

  fillOptionsAfterCustomerUpdate(customer:Customer){
    this.itens = [];
    customer.contracts.forEach(contract => {
      contract.itens.forEach(item => this.itens.push(item));
    });
    this.addresses = customer.addresses.filter(a => a.requiresCollection);

  }

  findCustomerInformation(customer?:Customer){
    let searchedCustomer = null;

    if(customer){
      searchedCustomer = this.customers.find(c => c.cpfCnpj === customer.cpfCnpj);
    }else{
      searchedCustomer = this.customers.find(c => c.cpfCnpj === this.form.value.customer);
    }


    this.fillOptionsAfterCustomerUpdate(searchedCustomer);
  }

  destroy(): void {
    this.unsubscribeToObservables();
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
