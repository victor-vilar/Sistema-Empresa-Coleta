import { Injectable } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ErrorsHelperService } from 'src/app/shared/services/erros-helper.service';

@Injectable({
  providedIn: 'root'
})
export class ResidueDetailErrosHelperService extends ErrorsHelperService {

  private readonly INVALID_RESIDUE_TYPE_MESSAGE = "O tipo do residuo não pode ser vazio!";
  private readonly INVALID_CLASS_TYPE_MESSAGE = "A classe do residuo não pode ser vazio!";

  constructor() {
    super();
  }

  public checkErrors(form:NgForm,...vargs: any[]): void {
    this.checkIfInputFieldsAreFilled(form,vargs[0],vargs[1]);
  }

  checkIfInputFieldsAreFilled(form:NgForm,isInvalidType:boolean,isInvalidDescription:boolean){
    if(!form.value.type.trim().length){
      isInvalidType = true;
      this.dialogService.openErrorDialog(this.INVALID_RESIDUE_TYPE_MESSAGE)
      throw Error(this.INVALID_RESIDUE_TYPE_MESSAGE);
    }

    if(!form.value.description.trim().length){
      isInvalidDescription = true;
      this.dialogService.openErrorDialog(this.INVALID_CLASS_TYPE_MESSAGE)
      throw Error(this.INVALID_CLASS_TYPE_MESSAGE);
    }
  }
}
