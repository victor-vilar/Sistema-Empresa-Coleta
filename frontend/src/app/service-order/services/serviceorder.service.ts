import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ServiceOrder } from 'src/app/shared/entities/ServiceOrder';
import { CrudBaseService } from 'src/app/shared/services/crudbase.service';

@Injectable({
  providedIn: 'root'
})
export class ServiceorderService extends CrudBaseService<ServiceOrder> {

  constructor() {
    super();
    this.rota ="service-orders"
  }

  //getAllByCustomerId(id:string):Observable<ServiceOrder[]>{  }
  //getAllByServiceExpectedDate(date:Date):Observable<ServiceOrder[]>{}


  getListOfNotExecuted():Observable<ServiceOrder[]>{
    return this.http.get<ServiceOrder[]>(this.BASE_URL + this.rota + "/opened",{withCredentials:true})
  }

  getCountOfNotExecuted():Observable<number>{
    return this.http.get<number>(this.BASE_URL + this.rota + "/opened/count",{withCredentials:true})
  }




}
