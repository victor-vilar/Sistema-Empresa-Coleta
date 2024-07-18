import { ContractStatus, ContractStatusType } from '../../shared/enums/ContractStatus';
import { ItemContract } from '../../shared/entities/ItemContract';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { Contract } from '../../shared/entities/Contract';
import { CrudBaseService } from 'src/app/shared/services/crudbase.service';
import { Mapper } from '../../shared/interfaces/mapper.mapper';
import { Schedule, ScheduleType } from 'src/app/shared/enums/Schedule';
import { ByCustomerSearcher } from '../interfaces/ByCustomerSearcher';

@Injectable({
  providedIn: 'root'
})
export class CustomerContractsService extends CrudBaseService<Contract> implements Mapper, ByCustomerSearcher<Contract>  {

  private route;
  constructor() {
    super();
    this.rota = 'contracts'
   }


  //get all contracts by customer id
  getAllByCustomerId(customerId:string | number):Observable<Contract[]>{
    this.route = this.BASE_URL + this.rota +'/all/'+customerId;
    return this.http.get<Contract[]>(this.route,{withCredentials:true});
  }

  /**
   *  delete a list of itens from api
   * @param itens an list of itens id that will be deleted
   * @returns the updated contract
   */
  deleteItensFromContract(itens:number[]):Observable<Contract>{
    this.route = this.BASE_URL + this.rota +'/deleteitens';
    return this.http.delete<any>(this.route,{withCredentials:true,body:itens});
  }


  // get all contracts that have a contractStatus equals to given param
  getAllContractsThatHaveContractStatusType(contractStatus:ContractStatusType):Contract[]{
    return this.list.filter(contract => contract.contractStatus === contractStatus);
  }

  //return from service list, contracts that have itens that have collection frequency equals to given param
  getAllItemContractThatHasScheduleType(schedule:ScheduleType):ItemContract[]{
      let listOfItens:ItemContract[] = [];

      this.list.forEach(contract => {
          contract.itens.filter(item => item.collectionFrequency.schedule === schedule)
          .forEach(item => listOfItens.push(item))


      })

      return listOfItens;
  }






  mapItens(list:any[]):any[]{

    return list.map(e => {

      let total = 0;
      e.itens.forEach(item=>total += item.itemValue * item.qtdOfResidue);

      return {
        id:e.id,
        number:e.number,
        beginDate:e.beginDate,
        endDate:e.endDate,
        totItens:e.itens.length,
        totalEmRs:total
      }
    })
  }
}
