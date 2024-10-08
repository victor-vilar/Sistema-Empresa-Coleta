import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { Supervisor } from '../../shared/entities/Supervisor';
import { CrudBaseService } from 'src/app/shared/services/crudbase.service';
import { Mapper } from '../../shared/interfaces/mapper.mapper';
import { ByCustomerSearcher } from '../interfaces/ByCustomerSearcher';

@Injectable({
  providedIn: 'root'
})
export class CustomerSupervisorService extends CrudBaseService<Supervisor> implements Mapper, ByCustomerSearcher<Supervisor>   {

  route:string;
  constructor() {
    super();
    this.rota='supervisors'
   }



  getAllByCustomerId(customerId:string | number):Observable<Supervisor[]>{
    this.route = this.BASE_URL + this.rota + '/by-customer/' + customerId;
    return this.http.get<Supervisor[]>(this.route,{withCredentials:true});
  }

  mapItens(list:any[]):any[]{
    return list.map(e =>{
      return {
        id:e.id,
        name:e.name,
        phoneNumber:e.phoneNumber,
        email:e.email
      }
    })
  }
}
