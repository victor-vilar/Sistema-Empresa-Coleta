import { ServiceOrderStatus } from '../../shared/enums/ServiceOrderStatus';
import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { ServiceOrder } from 'src/app/shared/entities/ServiceOrder';
import { ItensTableComponent } from 'src/app/shared/itens-table/itens-table.component';
import { DialogServiceService } from 'src/app/shared/services/dialog-service.service';
import { PdfTemplateComponent } from '../pdf-template/pdf-template.component';

@Component({
  selector: 'app-serviceorder-list',
  templateUrl: './serviceorder-list.component.html',
  styleUrls: ['./serviceorder-list.component.css']
})
export class ServiceorderListComponent extends ItensTableComponent {

  @Output()pdfEmitter:EventEmitter<ServiceOrder> = new EventEmitter<ServiceOrder>();

  constructor() {
    super();
    this.tableHeaders = ['n','cliente','data-prevista','data-executada','status','opcoes'];
  }


  sendOrderTogeneratePdf(order:ServiceOrder){
    this.pdfEmitter.emit(order);
  }



}
