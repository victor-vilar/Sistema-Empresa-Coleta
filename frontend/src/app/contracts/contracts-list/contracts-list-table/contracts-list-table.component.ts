import { Customer } from './../../../shared/entities/Customer';
import { CommunicationService } from './../../../shared/services/communication.service';
import { Component, Input, OnInit, ElementRef, ViewChild, inject } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { Router, ActivatedRoute } from '@angular/router';
import { CustomerContractsDetailComponent } from 'src/app/customer/customer-contracts/customer-contracts-detail/customer-contracts-detail.component';
import { CustomerService } from 'src/app/customer/services/customer.service';
import { Contract } from 'src/app/shared/entities/Contract';
import { ContractStatus, getContractStatusValues } from 'src/app/shared/enums/ContractStatus';
import { ItensTableComponent } from 'src/app/shared/itens-table/itens-table.component';
import { DialogServiceService } from 'src/app/shared/services/dialog-service.service';
import { MapperService } from 'src/app/shared/services/mapper.service';

@Component({
  selector: 'app-contracts-list-table',
  templateUrl: './contracts-list-table.component.html',
  styleUrls: ['./contracts-list-table.component.css']
})
export class ContractsListTableComponent extends ItensTableComponent {

  @ViewChild('search') search:HTMLSelectElement;
  customerService = inject(CustomerService);
  contractStatusEnumValues = getContractStatusValues();
  constructor(){
    super();
    this.tableHeaders = ['Id','Cliente','Numero','Data-Inicio', 'Data-Fim', 'Total-em-R$','Status','Opções'];
  };



  override sendObjectToEdit(contract){


    this.router.navigate(['/cliente',contract.customerId,'contratos','contrato',contract.id],
    {queryParams: {edit: true, dialog: true }})
    .then( response => {
      this.dialogService.openDialogPassingCustomerId(
        CustomerContractsDetailComponent,
        contract,
        contract.customerId,
        '/contratos',
        "800px",
        "100%");


    })


  }

  filterStatus(){
    if(this.search.value !== "TODOS"){
      this.dataSource.filter = this.search.value
    }

    this.updateDataSource();
  }

}
