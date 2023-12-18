import { Component, OnInit } from '@angular/core';
import { ServiceorderService } from './services/serviceorder.service';
import { ServiceOrderListTableComponentMapperService } from './services/service-order-list-table-component-mapper.service';
import { ActivatedRoute, ActivatedRouteSnapshot } from '@angular/router';
import { DialogServiceService } from '../shared/services/dialog-service.service';
import { ServiceorderCreateComponent } from './create/serviceorder.create.component';

@Component({
  selector: 'app-serviceorder',
  templateUrl: './serviceorder.component.html',
  styleUrls: ['./serviceorder.component.css']
})
export class ServiceorderComponent implements OnInit {

  headerForTables =['id','data-servico','cliente','quantidade','status','opcoes']
  constructor(
    public serviceOrderService:ServiceorderService,
    public mapper:ServiceOrderListTableComponentMapperService,
    public dialogService:DialogServiceService
    ) { }

  ngOnInit(): void {



  }

  editObject(object:any){

  }

  openAddNew(){
    this.dialogService.openDialog(ServiceorderCreateComponent,null,"/ordem-servico")
  }

}
