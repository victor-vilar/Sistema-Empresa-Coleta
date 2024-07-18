
import { Component, ViewChild, inject } from '@angular/core';
import { CustomerContractsDetailComponent } from 'src/app/customer/components/contracts/detail/customer-contracts-detail.component';
import { CustomerService } from 'src/app/customer/services/customer.service';
import { getContractStatusValues } from 'src/app/shared/enums/ContractStatus';
import { ItensTableComponent } from 'src/app/shared/itens-table/itens-table.component';


@Component({
  selector: 'app-contracts-list',
  templateUrl: './contracts-list.component.html',
  styleUrls: ['./contracts-list.component.css']
})
export class ContractsListComponent extends ItensTableComponent {

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
