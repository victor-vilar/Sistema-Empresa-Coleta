import { ItensTableComponent } from 'src/app/shared/itens-table/itens-table.component';
import { Observable } from 'rxjs';
import { DialogServiceService } from 'src/app/shared/services/dialog-service.service';
import { Router } from '@angular/router';
import { Component, Input, OnInit, SimpleChanges, Output, EventEmitter } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MapperService } from 'src/app/shared/services/mapper.service';


@Component({
  selector: 'app-customer-list',
  templateUrl: './customer-list.component.html',
  styleUrls: ['./customer-list.component.css']
})
export class CustomerListComponent extends ItensTableComponent {

  constructor(){
    super()
    this.tableHeaders =['CPF-CNPJ','Nome/Razão Social','Opções']
  }



}
