import { CustomerAddressService } from 'src/app/customer/services/customerAddress.service';
import { FormDetail } from 'src/app/shared/entities/FormDetail';
import { Component, OnInit, ViewChild, AfterViewInit, Input, Inject, inject } from '@angular/core';
import { NgForm } from '@angular/forms';
import { FullAddressFinderService } from 'src/app/customer/services/find-full-address.service';
import { Address } from 'src/app/shared/entities/Address';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { CustomerAddressesDetailErrorsHelperService } from '../services/customer-addresses-detail-errors-helper.service';
import { ErrorsHelperService } from 'src/app/shared/services/erros-helper.service';

@Component({
  selector: 'app-customer-addresses-detail',
  templateUrl: './customer-addresses-detail.component.html',
  styleUrls: ['./customer-addresses-detail.component.css']
})
export class CustomerAddressesDetailComponent extends FormDetail implements OnInit,AfterViewInit {


  @ViewChild('form') form: NgForm;

  searchedZipCodeErrorResponse:boolean = false;
  searchedZipCode="";

  private findFullAddress:FullAddressFinderService = inject(FullAddressFinderService);
  private addressService:CustomerAddressService = inject(CustomerAddressService);
  private errorsHelper:ErrorsHelperService = inject(CustomerAddressesDetailErrorsHelperService)

  @Input() isSubform:boolean=false;

  constructor(
    public dialogRef: MatDialogRef<CustomerAddressesDetailComponent>,
    @Inject(MAT_DIALOG_DATA) public data:any,
    ) {
      super();
    }



  ngOnInit(): void {
    this.onLoad(this.data);
    console.log(this.activatedRoute.snapshot);

  }

  ngAfterViewInit(): void {
    setTimeout(() =>{
      let requireCollection: boolean = this.objectToEdit.requiresCollection === 'sim'? true : false;
      this.form.setValue({
        zipCode:this.objectToEdit.zipCode,
        addressName: this.objectToEdit.addressName,
        addressNumber :this.objectToEdit.addressNumber,
        complement :this.objectToEdit.complement,
        city : this.objectToEdit.city,
        state : this.objectToEdit.state,
        requiresCollection:requireCollection
        })
    },100);
  }



  createObject():Address {
    return {
      addressName:this.form.value.addressName,
      addressNumber:this.form.value.addressNumber,
      complement:this.form.value.complement,
      zipCode:this.form.value.zipCode,
      city:this.form.value.city,
      state:this.form.value.state,
      requiresCollection:this.form.value.requiresCollection,
      customerId:this.clientCpfCnpj,
    }
  }

  save(): void {
    this.errorsHelper.checkErrors(this.form,this.searchedZipCodeErrorResponse,this.searchedZipCode,this.objectToEdit);
    this.dialogService.openProgressDialog();

    let observable$;
    let address = this.createObject();
    address.customerId = this.clientCpfCnpj;

    if(this.objectToEdit === undefined){
      observable$ = this.addressService.save(address);
    }else{
      address.id = this.objectToEdit.id;
      observable$ = this.addressService.update(address);
    }

    observable$.subscribe(this.saveAddressObserver());
    this.destroy();
  }

  saveAddressObserver(){
    return {
      next:(response) =>{
        this.dialogService.closeProgressSpinnerDialog();
        this.dialogService.openSucessDialog('Endereço salvo com sucesso !','/clientes');
        this.addressService.getAll();
      },
      error:(response) =>{
        this.dialogService.closeProgressSpinnerDialog();
        this.dialogService.openErrorDialog('Ocorreu algum erro !');
        console.log(response);
      }
    }
  }


  destroy(): void {
    this.unsubscribeToObservables();
    this.dialogRef.close();
  }


  //find the address information
   searchFullAddressInfo(){
    let response =  this.findFullAddress.getFullAddress(this.form.value.zipCode);
    response.then(address =>{

      this.fillFormInputs(address);
      this.searchedZipCodeErrorResponse = false;
      this.searchedZipCode = address.cep;
      this.searchedZipCode = this.searchedZipCode.replace("-","");
      this.dialogService.openSnackBar("Endereço encontrado com sucesso","Encontrado");

    }).catch(error => {
      this.searchedZipCodeErrorResponse = true;
      this.dialogService.openErrorDialog('Não foi possivel encontrar esse endereço !');
    })
  }

  fillFormInputs(address:any){
    this.form.setValue({
    zipCode:this.form.value.zipCode,
    addressName: address.logradouro,
    addressNumber :'',
    complement :'',
    city : address.localidade,
    state : address.uf,
    requiresCollection:this.form.value.requiresCollection
    })
  }

  clearForm(){
    this.form.reset();
  }


}
