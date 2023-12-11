import { ServiceOrderStatus } from './../../shared/enums/ServiceOrderStatus';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ServiceOrder } from 'src/app/shared/entities/ServiceOrder';
import { ItensTableComponent } from 'src/app/shared/itens-table/itens-table.component';
import { DialogServiceService } from 'src/app/shared/services/dialog-service.service';

@Component({
  selector: 'app-serviceorder-list',
  templateUrl: './serviceorder-list.component.html',
  styleUrls: ['./serviceorder-list.component.css']
})
export class ServiceorderListComponent extends ItensTableComponent {

  constructor(router: Router, dialogService: DialogServiceService) {
    super(router, dialogService)
  }

  statusStyle(order: ServiceOrder) {
    let object: any;
    console.log(order);
    console.log(ServiceOrderStatus.UNDONE);
    console.log(order.serviceOrderStatus === ServiceOrderStatus.DONE);
    //if contract status it is 'DONE'
    if (order.serviceOrderStatus === ServiceOrderStatus.DONE) {
      object = { backgroundColor: '#D5F5E3', color: '#28dcb8', textAlign: 'center', padding: '10px', borderRadius: '3px' };
    }

    //if contract status is 'UNDONE'
    if (order.serviceOrderStatus === ServiceOrderStatus.UNDONE) {
      object = { backgroundColor: '#ffff99', color: '#ff9900', textAlign: 'center', padding: '10px', borderRadius: '3px' };
      console.log('tttttt')
    }

    //if contract status is 'PENDENTE_RENOVAÇÃO'
    if (order.serviceOrderStatus === ServiceOrderStatus.CANCELLED) {
      object = { backgroundColor: '#ffff99', color: '#ff9900', textAlign: 'center', padding: '10px', borderRadius: '3px' };
    }


    return object;
  }


}
