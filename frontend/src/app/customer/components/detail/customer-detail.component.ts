import { CustomerService } from 'src/app/customer/services/customer.service';
import { Component, Inject, OnInit, ViewChild, AfterViewInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { FormDetail } from 'src/app/shared/entities/FormDetail';
import { Customer } from 'src/app/shared/entities/Customer';
import { ResidueDetailComponent } from '../../../residue/components/detail/residue-detail.component';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';





@Component({
  selector: 'app-customer-detail',
  templateUrl: './customer-detail.component.html',
  styleUrls: ['./customer-detail.component.css']
})
export class CustomerDetailComponent extends FormDetail implements OnInit, AfterViewInit  {

  @ViewChild('singInForm')form: NgForm;
  rota: string = 'customer';

  //error handlers
  invalidCpfCnpj = false;
  invalidCpfCnpjMessage:string;
  invalidCustomerName = false;
  invalidCustomerNameMessage:string;



  constructor(
    private service:CustomerService,
    public dialogRef: MatDialogRef<CustomerDetailComponent>,
    @Inject(MAT_DIALOG_DATA) public data:any,
    ) {super(); }



  ngOnInit(): void {
    this.onLoad(this.data);
  }

  ngAfterViewInit(): void {
    setTimeout(() =>{
      this.form.setValue({
        cpfCnpj:this.objectToEdit.cpfCnpj,
        razaoSocial:this.objectToEdit.nameCompanyName,
      })},100
    )

  }

  createObject():Customer{
    return {
      cpfCnpj:this.form.value.cpfCnpj,
      nameCompanyName:this.form.value.razaoSocial,
    }

  }

  checkIfRazaoSocialAreFilled(){
    if(!this.form.value.razaoSocial.trim().length){
      this.invalidCustomerName = true;
      this.invalidCustomerNameMessage = 'O nome do cliente não pode ser vazio!'
      throw Error('O nome do cliente não pode ser vazio!');
    }
  }

  resetInvalidProperties(){
    this.invalidCustomerName = false;
    this.invalidCpfCnpj = false;
  }

  save(): void {

    this.resetInvalidProperties();
    this.checkIfRazaoSocialAreFilled();

    this.dialogService.openProgressDialog();
    let customer = this.createObject();
    let obervable$;

    if(this.idOfEditedItem === undefined){
      obervable$ =this.service.save(customer);
    }else{
      obervable$ =this.service.update(customer)
    }

    obervable$.subscribe(this.customerObserver());

  }


  destroy(): void {
    this.unsubscribeToObservables();
    this.dialogRef.close();
  }

  cleanForm(){
    this.form.reset();
  }

  private customerObserver(){

    return{
      next:(response)=>{
        this.dialogService.closeProgressSpinnerDialog();
        this.dialogService.openSucessDialog('Cliente salvo com sucesso !','/clientes');
        this.service.getAll();
        this.destroy();
      },
      error:(response)=>{
        this.dialogService.closeProgressSpinnerDialog();

        if(response.error.message === "This CPF or CNPJ is Invalid"){
          this.invalidCpfCnpj = true;
          this.invalidCpfCnpjMessage = "Esse é um CPF/CNPJ invalido";
        }
        if(response.error.message === "This Cpf/Cnpj already exists in database"){
          this.invalidCpfCnpj = true;
          this.invalidCpfCnpjMessage = "Um cliente com esse CPF ou CNPJ ja esta cadastrado";
        }

        this.dialogService.openErrorDialog(this.invalidCpfCnpjMessage);
      }
    }
  }
}
