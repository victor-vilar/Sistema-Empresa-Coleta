
import { Component, inject } from '@angular/core';
import { ServiceorderService } from '../../services/serviceorder.service';
import { ServiceOrderListTableComponentMapperService } from '../../services/service-order-list-table-component-mapper.service';
import { ServiceorderDetailComponent } from '../detail/serviceorder.detail.component';
import { MainComponentEntityOfCustomer } from 'src/app/shared/entities/MainComponentEntityOfCustomer';
import { PdfTemplateComponent } from '../pdf/pdf-template.component';


@Component({
  selector: 'app-serviceorder',
  templateUrl: './serviceorder.component.html',
  styleUrls: ['./serviceorder.component.css']
})
export class ServiceorderComponent extends MainComponentEntityOfCustomer {

  serviceOrderService:ServiceorderService = inject(ServiceorderService);

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
       path: './novo',
       title:"Novo " + this.path
      });
  }


  openDialog(): void {
    this.dialogService.openDialog(ServiceorderDetailComponent,this.objectToEdit,"/ordem-servico","1200px","600px")
    this.objectToEdit = null;
  }


  generatePdf(order:any){
    this.dialogService.openDialog(PdfTemplateComponent,order,null,"0px","0px");
    this.dialogService.openProgressDialog();
    setTimeout(() =>{
      this.dialogService.closeProgressSpinnerDialog();
    },10);

  }





}


