import { Injectable } from '@angular/core';
import { CrudBaseService } from './crudbase.service';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FormDetailHelperService {

  constructor() { }

  public save(object:any,service:CrudBaseService<any>,objectIsCustomer:boolean=false):Observable<any>{

    // if the object is a customer its id it is de cpfCnpj propertie
    if(objectIsCustomer){

      if(object.cpfCnpj === null || object.cpfCnpj === ""){
          return service.save(object);
      }else{
          return service.update(object);
      }

    }
    else{

      if(object.id === null || object.id === ""){
        return service.save(object);
      }else{
        return service.update(object);
      }
    }
  }

}
