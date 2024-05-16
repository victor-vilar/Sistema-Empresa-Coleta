import { Injectable } from '@angular/core';
import { CustomerService } from 'src/app/customer/services/customer.service';
import { ServiceOrderStatus } from 'src/app/shared/enums/ServiceOrderStatus';
import { Mapper } from 'src/app/shared/interfaces/mapper.mapper';

@Injectable({
  providedIn: 'root'
})
export class ServiceOrderListTableComponentMapperService implements Mapper {

  constructor(private customerService:CustomerService) { }

  mapItens(list: any[]): any[] {
    return list.map(order => {

      return {
          id:order.id,
          serviceDate:order.serviceExpectedDate,
          customer: this.customerService.list.find(customer => customer.cpfCnpj === order.customerId),
          quantity:order.amountCollected,
          serviceOrderStatus:order.serviceOrderStatus,
          address:order.address,
          
      }

    })
  }
}
