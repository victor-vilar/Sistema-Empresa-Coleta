import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { ServiceOrder } from 'src/app/shared/entities/ServiceOrder';
import { ItensTableComponent } from 'src/app/shared/itens-table/itens-table.component';


@Component({
  selector: 'app-serviceorder-list',
  templateUrl: './serviceorder-list.component.html',
  styleUrls: ['./serviceorder-list.component.css']
})
export class ServiceorderListComponent extends ItensTableComponent {

  @Output()pdfEmitter:EventEmitter<ServiceOrder> = new EventEmitter<ServiceOrder>();

  constructor() {
    super();
    this.tableHeaders = ['n','status','cliente','data-prevista','data-executada','opcoes'];
  }


  sendOrderTogeneratePdf(order:ServiceOrder){
    this.pdfEmitter.emit(order);
  }



}
