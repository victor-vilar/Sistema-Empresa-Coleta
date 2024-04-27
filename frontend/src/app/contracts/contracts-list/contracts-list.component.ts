import { Component, OnInit, inject } from '@angular/core';
import { CustomerService } from '../../customer/services/customer.service';
import { CustomerContractsService } from '../../customer/services/customerContracts.service';
import { DialogServiceService } from 'src/app/shared/services/dialog-service.service';
import { ActivatedRoute } from '@angular/router';
import { ContractsListTableComponentMapperService } from './contracts-list-table-component-mapper.service';

@Component({
  selector: 'app-contracts-list',
  templateUrl: './contracts-list.component.html',
  styleUrls: ['./contracts-list.component.css']
})
export class ContractsListComponent implements OnInit {

  headerForTables:string[] = [];
  title = "Contratos"
  objectToEdit:any;
  contractService:CustomerContractsService = inject(CustomerContractsService);
  mapper:ContractsListTableComponentMapperService = inject(ContractsListTableComponentMapperService);


  constructor(){}
  ngOnInit(): void {
    this.headerForTables = ['Id','Cliente','Numero','Data-Inicio', 'Data-Fim', 'Total-em-R$','Status','Opções'];
  }




}
