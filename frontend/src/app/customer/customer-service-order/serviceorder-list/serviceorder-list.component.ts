import { ServiceOrderStatus } from '../../../shared/enums/ServiceOrderStatus';
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

  constructor() {
    super();
    this.tableHeaders = ['id','data-servico','cliente','quantidade','status','opcoes'];
  }



}
