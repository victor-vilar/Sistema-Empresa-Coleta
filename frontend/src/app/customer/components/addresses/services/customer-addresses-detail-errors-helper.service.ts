import { Injectable } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ErrorsHelperService } from 'src/app/shared/services/erros-helper.service';

@Injectable({
  providedIn: 'root'
})
export class CustomerAddressesDetailErrorsHelperService extends ErrorsHelperService {

  private readonly INVALID_ADDRESS = 'Não foi possivel encontrar esse endereço, insira um endereço valido !';
  private readonly DIFFERENT_CEP = 'O cep digitado é diferente do cep anteriormente pesquisado';

  constructor() { super(); }

  public override checkErrors(form: NgForm, ...vargs: any[]): void {

    if((vargs[0] || vargs[1] ==="") && vargs[2] === undefined){
      this.dialogService.openErrorDialog(this.INVALID_ADDRESS);
      throw new Error(this.INVALID_ADDRESS);
    }

    if((form.value.zipCode !== vargs[1]) && vargs[2] === undefined ){
      this.dialogService.openErrorDialog(this.DIFFERENT_CEP);
      throw new Error(this.DIFFERENT_CEP);
    }
  }
}
