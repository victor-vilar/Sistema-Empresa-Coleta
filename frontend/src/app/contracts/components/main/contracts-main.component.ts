import { Component, OnInit, inject } from '@angular/core';
import { CustomerContractsService } from '../../../customer/services/customer-contracts.service';
import { ContractsListComponentMapperService } from '../../services/contracts-list-component-mapper.service';

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
  mapper:ContractsListComponentMapperService = inject(ContractsListComponentMapperService);


  constructor(){}
  ngOnInit(): void {
    
  }




}
