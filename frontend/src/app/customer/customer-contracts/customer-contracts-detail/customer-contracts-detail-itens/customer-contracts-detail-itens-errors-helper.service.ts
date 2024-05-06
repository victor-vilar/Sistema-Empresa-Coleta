import { Injectable, inject } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ItemContract } from 'src/app/shared/entities/ItemContract';
import { DialogServiceService } from 'src/app/shared/services/dialog-service.service';

/**
 * Serviço helper para o componente CustomerContracsDetailItens, que é o componente de detalhes(cadastro) dos
 * itens do contrato.
 * Esse serviço é usado para auxiliar o componente a encontrar erros no cadastro de novos itens a algum contrato.
 */
@Injectable({
  providedIn: 'root'
})
export class CustomerContractsDetailItensErrorsHelperService {


  dialogService:DialogServiceService = inject(DialogServiceService);
  private readonly ITEM_ALREADY_EXIST_MESSAGE = 'Um item com os mesmo atributos já existe dentro da lista !'

  constructor() { }

  /**
   * checa erros ao adicionar novos itens
   * @param itemContractList lista de itens de contrato
   * @param item item que sera adicionado.
   */
  public checkErrorsOnAddNewItemContract(form:NgForm, itemContractList:ItemContract[], item:ItemContract):void {

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
