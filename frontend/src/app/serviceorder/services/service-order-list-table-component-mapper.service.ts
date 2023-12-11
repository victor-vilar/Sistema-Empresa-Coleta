import { Injectable } from '@angular/core';
import { CustomerService } from 'src/app/customer/services/customer.service';
import { Mapper } from 'src/app/shared/interfaces/mapper.mapper';

@Injectable({
  providedIn: 'root'
})
export class ServiceOrderListTableComponentMapperService implements Mapper {

  constructor(private customerService:CustomerService) { }

  mapItens(list: any[]): any[] {
    return list.map(order => {
      console.log(order);
      return {
          id:order.id,
          serviceDate:order.serviceExpectedDate,
          customer: this.customerService.list.find(customer => customer.cpfCnpj === order.customerId),
          quantity:order.amountCollected,
          serviceOrderStatus:order.serviceOrderStatus
      }

    })
  }
}
