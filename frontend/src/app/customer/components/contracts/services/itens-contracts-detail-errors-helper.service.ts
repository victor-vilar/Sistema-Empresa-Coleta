import { Injectable, inject } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ItemContract } from 'src/app/shared/entities/ItemContract';
import { DialogServiceService } from 'src/app/shared/services/dialog-service.service';
import { ErrorsHelperService } from 'src/app/shared/services/erros-helper.service';

/**
 * Serviço helper para o componente CustomerContracsDetailItens, que é o componente de detalhes(cadastro) dos
 * itens do contrato.
 * Esse serviço é usado para auxiliar o componente a encontrar erros no cadastro de novos itens a algum contrato.
 */
@Injectable({
  providedIn: 'root'
})
export class ItensContractsDetailErrorsHelperService extends ErrorsHelperService {

  private readonly EMPTY_FIELDS_MESSAGE = 'É necessario prencher todos os campos para adicionar um resíduo !!!'
  private readonly NOT_NUMBERS_MESSAGE = 'Os campos de quantidade e valor, dos campos do cadastro de resíduos, devem ser do tipo número e serem maiores do que zero'
  private readonly ITEM_ALREADY_EXIST_MESSAGE = 'Um item com os mesmo atributos já existe dentro da lista !'
  constructor() {super(); }


  public checkErrors(form:NgForm,...vargs:any[]):void{
    this.checkErrorsOnAddNewItemContract(form,vargs[0],vargs[1]);
  }
  /**
   * checa erros ao adicionar novos itens
   * @param itemContractList lista de itens de contrato
   * @param item item que sera adicionado.
   */
  public checkErrorsOnAddNewItemContract(form:NgForm, itemContractList:ItemContract[], item:ItemContract):void {

    //checa se todos os campos do resíduo estão preenchidos
    Object.values(form.controls).forEach(e =>{
      if((e.value === '' || e.value === null) && (e !== form.controls['days'])){
          this.dialogService.openErrorDialog(this.EMPTY_FIELDS_MESSAGE);
          throw Error(this.EMPTY_FIELDS_MESSAGE);
      }
    })

    //checa se os campos são numeros
    if(isNaN(form.value.equipmentQuantity) || isNaN(form.value.quantity) || isNaN(form.value.itemValue) || form.value.quantity <= 0 || form.value.itemValue <=0){
      this.dialogService.openErrorDialog(this.NOT_NUMBERS_MESSAGE);
      throw Error(this.NOT_NUMBERS_MESSAGE);
    }

    //checa se há algum item igual ja salvo na lista de itens
    let itemAlreadyExist = itemContractList.some(e => this.itemContractCompare(e, item));
    if(itemAlreadyExist){
            this.dialogService.openErrorDialog(this.ITEM_ALREADY_EXIST_MESSAGE);
            throw Error(this.ITEM_ALREADY_EXIST_MESSAGE);
    }

  }

  //checar se existe item com os mesmos atributos na lista.
  public itemContractCompare(item1:ItemContract,item2:ItemContract){

      if(item1.id !== item2.id){
        return false;
      }

      if(item1.equipment !== item2.equipment){
        return false;
      }

      if(item1.residue !== item2.residue){
        return false;
      }

      if(item1.qtdOfResidue !== item2.qtdOfResidue){
        return false;
      }

      if(item1.itemValue !== item2.itemValue){
        return false;
      }

      if(item1.description !== item2.description){
        return false;
      }

      return true
    }
}
