import { Component, OnInit, inject } from '@angular/core';
import { CustomerContractsService } from '../../../customer/services/customerContracts.service';
import { ContractsListTableComponentMapperService } from '../../services/contracts-list-table-component-mapper.service';

@Component({
  selector: 'app-contracts-main',
  templateUrl: './contracts-main.component.html',
  styleUrls: ['./contracts-main.component.css']
})
export class ContractsMainComponent implements OnInit {

  headerForTables:string[] = [];
  title = "Contratos"
  objectToEdit:any;
  contractService:CustomerContractsService = inject(CustomerContractsService);
  mapper:ContractsListTableComponentMapperService = inject(ContractsListTableComponentMapperService);


  constructor(){}
  ngOnInit(): void {
    
  }




}
