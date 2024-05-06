import { Injectable } from '@angular/core';
import { CrudBaseService } from './crudbase.service';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FormDetailHelperService {

  constructor() { }

  public save(object:any,service:CrudBaseService<any>,objectIsCustomer:boolean=false):Observable<any>{
      //todo
      throw new Error('Not implemented yet')
  }

}
