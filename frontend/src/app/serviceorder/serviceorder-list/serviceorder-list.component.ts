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
    object = {
      textAlign: 'center',
      padding: '10px',
      borderRadius: '3px'
    }

    //if contract status it is 'DONE'
    if (order.serviceOrderStatus === ServiceOrderStatus.DONE) {
      object.backgroundColor = '#D5F5E3';
      object.width="100%"
      object.color = '#28dcb8';

    }

    //if contract status is 'UNDONE'
    if (order.serviceOrderStatus === ServiceOrderStatus.UNDONE) {
      object.backgroundColor = '#ff99';
      object.width="100%"
      object.color = '#black';
    }

    //if contract status is 'PENDENTE_RENOVAÇÃO'
    if (order.serviceOrderStatus === ServiceOrderStatus.CANCELLED) {
      object.backgroundColor = '#ffff99';
      object.width="100%"
      object.color = '#black';
    }


    return object;
  }


}
