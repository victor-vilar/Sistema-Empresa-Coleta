import { CommunicationService } from '../../shared/services/communication.service';
import { Component, ElementRef, OnInit, ViewChild, inject } from '@angular/core';
import { ServiceorderService } from './services/serviceorder.service';
import { ServiceOrderListTableComponentMapperService } from './services/service-order-list-table-component-mapper.service';
import { Router } from '@angular/router';
import { DialogServiceService } from '../../shared/services/dialog-service.service';
import { ServiceorderDetailComponent } from './customer-service-order-detail/serviceorder.detail.component';
import { PdfBuilderService } from './services/pdf-builder.service';

import { Mapper } from 'src/app/shared/interfaces/mapper.mapper';

@Component({
  selector: 'app-serviceorder',
  templateUrl: './serviceorder.component.html',
  styleUrls: ['./serviceorder.component.css']
})
export class ServiceorderComponent implements OnInit {

  showRouterOutlet:boolean = false;
  serviceOrderService:ServiceorderService = inject(ServiceorderService);
  mapper:Mapper = inject(ServiceOrderListTableComponentMapperService);
  private dialogService:DialogServiceService = inject(DialogServiceService);
  private communicationService:CommunicationService = inject(CommunicationService);
  private router:Router = inject(Router)
  private pdfBuilder:PdfBuilderService;

  constructor() { }

  ngOnInit(): void {

  }

  editObject(object:any){

    this.showRouterOutlet = true;
    let serviceOrder = this.serviceOrderService.list.find(o => o.id === object.id);
    console.log(serviceOrder);
    this.router.navigate(["ordem-servico","pdf"]).then(() =>{

      setTimeout(() => {
        this.communicationService.sendData(serviceOrder);
        //this.showRouterOutlet=false;
      },10)

    })

  }

  openAddNew(){
    this.dialogService.openDialog(ServiceorderDetailComponent,null,"/ordem-servico")
  }

}


