import { Injectable } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ErrorsHelperService } from 'src/app/shared/services/erros-helper.service';

@Injectable({
  providedIn: 'root'
})
export class ServiceorderDetailComponentErrorsHelperService extends ErrorsHelperService {

  private readonly EMPTY_FIELD ="Todos os campos devem ser preenchidos.";
  constructor() { super(); }

  public override checkErrors(form: NgForm, ...vargs: any[]): void {
    this.checkFilledFields(form);
  }

  private checkFilledFields(form:NgForm){

    Object.values(form.controls).forEach(e =>{
      if(e.value === ""){
        this.dialogService.openErrorDialog(this.EMPTY_FIELD);
        throw new Error(this.EMPTY_FIELD);
      }
    })

  }
}
