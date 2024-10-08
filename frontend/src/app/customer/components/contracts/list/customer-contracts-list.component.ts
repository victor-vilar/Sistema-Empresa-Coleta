import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { ItensTableComponent } from 'src/app/shared/itens-table/itens-table.component';
import { DialogServiceService } from 'src/app/shared/services/dialog-service.service';
import { MapperService } from 'src/app/shared/services/mapper.service';

@Component({
  selector: 'app-customer-contracts-list',
  templateUrl: './customer-contracts-list.component.html',
  styleUrls: ['./customer-contracts-list.component.css']
})
export class CustomerContractsListComponent extends ItensTableComponent{

  constructor(){
    super()
    this.tableHeaders = ['Numero','Data-Inicio', 'Data-Fim', 'Total-de-Itens', 'Total-em-R$','Opções'];
}




}
