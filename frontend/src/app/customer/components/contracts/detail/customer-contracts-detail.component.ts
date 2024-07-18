
import { ContractStatus } from '../../../../shared/enums/ContractStatus';
import { DialogServiceService } from 'src/app/shared/services/dialog-service.service';
import { Router, ActivatedRoute, UrlTree, RouterStateSnapshot } from '@angular/router';
import { Contract } from 'src/app/shared/entities/Contract';
import { ItemContract, itemContractListForTests } from 'src/app/shared/entities/ItemContract';
import { Component, Inject, OnInit, ViewChild, inject } from '@angular/core';
import { NgForm } from '@angular/forms';
import { CustomerContractsService } from 'src/app/customer/services/customerContracts.service';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { getContractStatusValues } from 'src/app/shared/enums/ContractStatus';
import { ItensContractsDetailComponent } from '../util/detail-itens-contract/itens-contracts-detail.component';
import { FormDetail } from 'src/app/shared/entities/FormDetail';
import { ErrorsHelperService } from 'src/app/shared/services/erros-helper.service';
import { CustomerContractsDetailErrorsHelperService } from '../services/customer-contracts-detail-errors-helper.service';


@Component({
  selector: 'app-customer-contracts-detail',
  templateUrl: './customer-contracts-detail.component.html',
  styleUrls: ['./customer-contracts-detail.component.css']
})
export class CustomerContractsDetailComponent extends FormDetail implements OnInit {

  //form
  @ViewChild('form') form:NgForm;
  @ViewChild('itensChild') child:ItensContractsDetailComponent;
  contractStatusEnumValues;



  //services
  contractService:CustomerContractsService = inject(CustomerContractsService);
  errorsHelper:ErrorsHelperService = inject(CustomerContractsDetailErrorsHelperService);
  //list of itens of a contract
  itemContractList:ItemContract[] = [];
  //saves temporaly deleted itens from contract list to delete later
  deletedSavedItensIdList:number[] =[]
  //errors
  allFieldsMustBeFilledError:boolean = false;





  constructor(public dialogRef: MatDialogRef<CustomerContractsDetailComponent>,
              @Inject(MAT_DIALOG_DATA) public data:any)
              {
                super();
              }



  ngOnInit(): void {
    this.onLoad();
    this.contractStatusEnumValues = getContractStatusValues();
  }

  //onload method to know if form going to be on 'edit' mode or 'new' mode
  override onLoad(): void {

    //getting cpf/cnpj(id) from customer
    this.clientCpfCnpj = this.data.clientCpfCnpj;
    //if the object (contract) is not null
    if(this.data.objectToEdit !== undefined && this.data.objectToEdit !== null){

      //getting contract
      this.objectToEdit = this.contractService.list.find(c =>c.id === this.data.objectToEdit.id);

    }

  }


  ngAfterViewInit(): void {

    setTimeout(() =>{
      if(this.objectToEdit  != null){
        this.form.setValue({
          contractNumber:this.objectToEdit.number,
          beginDate:new Date(this.objectToEdit.beginDate),
          endDate:new Date(this.objectToEdit.endDate),
          contractStatus:this.objectToEdit.contractStatus,
        })
        this.itemContractList = this.objectToEdit.itens;


      }
    },200);




  }

  //creates a contract from form fields
  createObject():any{
        return {
          number:this.form.value.contractNumber,
          beginDate:this.form.value.beginDate,
          endDate:this.form.value.endDate,
          contractStatus:this.form.value.contractStatus
        }
      }


  //saves contract at database
  save(){


    this.errorsHelper.checkErrors(this.form,this.child.itemContractList)

    this.dialogService.openProgressDialog();


    //create a contract object
    let contract = this.createObject();

    //adding list of itens to contract, that have been transformed;
    contract.itens = this.child.itemContractListMapper();
    contract.customerId = this.clientCpfCnpj;

    //creates a contractObserver
    let contractObserver;

    //observervable$
    let observervable$;

    //if the idOfEditedItem === undefined means its a new contract not a edited one
    if(this.objectToEdit === null || this.objectToEdit === undefined){

      contractObserver = this.contractCreateObserver();
      observervable$ = this.contractService.save(contract);

      //if it's undefined it's just updating
    }else{

      //fill empty contract fields to make the update
      contract.id = this.objectToEdit.id;
      contract.customerId = this.objectToEdit.customerId;

      contractObserver = this.contractUpdateObserver();

      //put on api
      observervable$ = this.contractService.update(contract);
    }

    //executing observable
    observervable$.subscribe(contractObserver);

    this.destroy();
  }


  //function to check if endDate is bigger than beginDate in matDatepickerFilter
  endDateFilter = (date: Date | null): boolean => {
    if (date && this.form.value.beginDate) {
      return date >= this.form.value.beginDate;
    }
    return true;
  }



  /**
   * if some itens are deleted from the contract frontend list, it will be deleted from backend list
   */
  deleteItemsFromApi(){

      this.contractService.deleteItensFromContract(this.deletedSavedItensIdList)
      .subscribe(this.deleteItemFromContractObserver());
  }

  //navigates to another page
  destroy(): void {
    this.unsubscribeToObservables();
    this.dialogRef.close();

  }

  //observer to manipulate observable subscription
  //creates contract
  contractCreateObserver():any{
    return{
      next:(response) =>{
        //colse progress dialog
        this.dialogService.closeProgressSpinnerDialog();
        //show success message
        this.dialogService.openSucessDialog('Contrato salvo com sucesso !','/clientes');
        //update contract list
        this.contractService.getAll();

      },
      error:(error)=>{
        //close progress dialog
        this.dialogService.closeProgressSpinnerDialog();
        //show error message
        this.dialogService.openErrorDialog(error.message);
        console.log(error);
      }
    }
  }

  contractUpdateObserver():any{
    return{
      next:(response) =>{

        if(this.deletedSavedItensIdList.length > 0){
          this.deleteItemsFromApi();
        }else{

          this.dialogService.closeProgressSpinnerDialog();
          this.dialogService.openSucessDialog('Contrato atualizado com sucesso !','/clientes');
          this.contractService.getAll();
        }

      },
      error:(error)=>{
        //close progress dialog
        this.dialogService.closeProgressSpinnerDialog();
        //show error message
        this.dialogService.openErrorDialog(error.message);
      }
    }
  }


  //deletes contract
  deletesContractObserver():any{
    return{
      next:(response) =>{
        //close progress dialog
        this.dialogService.closeProgressSpinnerDialog();
        //show success message
        this.dialogService.openSucessDialog('Contrato deletado com sucesso !','/clientes');
      },
      error:(error)=>{
        //close progress dialog
        this.dialogService.closeProgressSpinnerDialog();
        //show error message
        this.dialogService.openErrorDialog(error.message);
      }
    }
  }

  //observer to do after eliminates a itemContract from databse;
  deleteItemFromContractObserver():any{
    return{
      next:(response) =>{

        //show success message
        this.dialogService.closeProgressSpinnerDialog();
        this.dialogService.openSucessDialog('Contrato atualizado com sucesso !','/clientes');
        this.contractService.getAll();

      },
      error:(error)=>{
        this.dialogService.closeProgressSpinnerDialog();
        console.log(error);
        this.dialogService.openErrorDialog(error);
      }
    }
  }
  //====================

  //override
  canDeactivate(){
    //if contract form it is dirty and the form button was not pressed to save the alteration
    //display message asking the if the user wants to save
    if(this.form.dirty){
        this.dialogService.openConfirmCloseDialog("Deseja sair sem salvar ?").subscribe(response =>{
          //if the user doesn't want to save, destroy component
          if(response){
            this.destroy();
          }
        })
    }else{
      this.destroy();
    }




  }

}






