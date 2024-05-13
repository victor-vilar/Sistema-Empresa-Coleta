import { Injectable, inject } from '@angular/core';
import { DialogServiceService } from './dialog-service.service';
import { NgForm } from '@angular/forms';

@Injectable({
  providedIn: 'root'
})
export abstract class ErrorsHelperService {

  protected dialogService:DialogServiceService = inject(DialogServiceService);
  constructor() { }

  public abstract checkErrors(form:NgForm,...vargs:any[]):void;
}
