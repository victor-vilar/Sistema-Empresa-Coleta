import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ItensTableComponent } from 'src/app/shared/itens-table/itens-table.component';
import { DialogServiceService } from 'src/app/shared/services/dialog-service.service';
import { MapperService } from 'src/app/shared/services/mapper.service';

@Component({
  selector: 'app-customer-supervisors-list',
  templateUrl: './customer-supervisors-list.component.html',
  styleUrls: ['./customer-supervisors-list.component.css']
})
export class CustomerSupervisorsListComponent extends ItensTableComponent {

  constructor(){
    super()
    this.tableHeaders = ['Nome','Telefone','Email','Opções'];
}


}
