import { Injectable } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ItemContract } from 'src/app/shared/entities/ItemContract';
import { ErrorsHelperService } from 'src/app/shared/services/erros-helper.service';

@Injectable({
  providedIn: 'root'
})
export class CustomerContractsDetailErrorsHelperService extends ErrorsHelperService {


  private readonly EMPTY_ITEM_LIST = 'O contrato deve possuir pelo menos um item!!';
  private readonly FORM_FIELDS_EMPTY = 'É necessario prencher todos os campos para adicionar um resíduo !!!';

  constructor() { super(); }

  public override checkErrors(form:NgForm,...vargs: any[]): void {

    this.checkIfItemContractFromInputsAreFilled(form);
    this.checkIfContractHasItens(vargs[0]);

  }


  /**
   * Verifica se o contrato possui pelo menos um item
   * Não deve se criar contratos sem itens
   * @param itemContractList lista de itens do contrato
   */
  private checkIfContractHasItens(itemContractList:ItemContract[]){
    if(itemContractList.length === 0 ){
      this.dialogService.openErrorDialog(this.EMPTY_ITEM_LIST);
      throw new Error(this.EMPTY_ITEM_LIST);
    }

  }

  /**
   * Verifica se os campos dos contratos estão todos preenchidos
   * @param form formulário que será verificado.
   */
  private checkIfItemContractFromInputsAreFilled(form:NgForm){
    Object.values(form.controls).forEach(e =>{
      if(e.value === '' || e.value === null){
          this.dialogService.openErrorDialog(this.FORM_FIELDS_EMPTY);
          throw new Error(this.FORM_FIELDS_EMPTY);
      }
    })
  }
}
