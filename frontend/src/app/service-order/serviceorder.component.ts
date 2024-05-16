import { CommunicationService } from '../shared/services/communication.service';
import { Component, ElementRef, OnInit, ViewChild, inject } from '@angular/core';
import { ServiceorderService } from './services/serviceorder.service';
import { ServiceOrderListTableComponentMapperService } from './services/service-order-list-table-component-mapper.service';
import { Router } from '@angular/router';
import { DialogServiceService } from '../shared/services/dialog-service.service';
import { ServiceorderDetailComponent } from './service-order-detail/serviceorder.detail.component';
import { PdfBuilderService } from './services/pdf-builder.service';

import { Mapper } from 'src/app/shared/interfaces/mapper.mapper';
import { MainComponentEntityOfCustomer } from 'src/app/shared/entities/MainComponentEntityOfCustomer';
import { ServiceOrderAddCollectionComponent } from './service-order-add-collection/service-order-add-collection.component';

@Component({
  selector: 'app-serviceorder',
  templateUrl: './serviceorder.component.html',
  styleUrls: ['./serviceorder.component.css']
})
export class ServiceorderComponent extends MainComponentEntityOfCustomer {

  serviceOrderService:ServiceorderService = inject(ServiceorderService);
  private communicationService:CommunicationService = inject(CommunicationService);


  constructor() {
    super();
    this.mapper = inject(ServiceOrderListTableComponentMapperService);
   }

  override ngOnInit(): void {
    super.ngOnInit();
    this.title = 'Ordens de Serviço';
    this.path = 'ordem-servico';
    this.pathToOperations.push(
      {name:"Cadastrar nova Ordem",
       path: this.path + '/novo',
       title:"Novo " + this.path
      });
  }


  openDialog(): void {
    if(this.activatedRoute.snapshot.queryParams['edit']){
      this.dialogService.openDialog(ServiceOrderAddCollectionComponent,this.objectToEdit,"/ordem-servico","800px","400px")
    }else{
       this.dialogService.openDialog(ServiceorderDetailComponent,this.objectToEdit,"/ordem-servico","800px","800px")
     }


    this.objectToEdit = null;
  }

}


