import { Injectable } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ErrorsHelperService } from 'src/app/shared/services/erros-helper.service';

@Injectable({
  providedIn: 'root'
})
export class ServiceorderDetailComponentErrorsHelperService extends ErrorsHelperService {


  constructor() { super(); }

  public override checkErrors(form: NgForm, ...vargs: any[]): void {
    throw new Error('Method not implemented.');
  }
}
