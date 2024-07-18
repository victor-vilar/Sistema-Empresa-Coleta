import { CustomerContractsDetailComponent } from '../detail/customer-contracts-detail.component';
import { Component, inject } from '@angular/core';
import { CustomerContractsService } from 'src/app/customer/services/customerContracts.service';
import { CustomerContractsByCustomerTableMapperService } from '../services/customer-contracts-by-customer-table-mapper.service';
import { CommunicationService } from 'src/app/shared/services/communication.service';
import { MainComponentEntityOfCustomer } from 'src/app/shared/entities/MainComponentEntityOfCustomer';

@Component({
  selector: 'app-customer-contracts-main',
  templateUrl: './customer-contracts-main.component.html',
  styleUrls: ['./customer-contracts-main.component.css']
})
export class CustomerContractsMainComponent extends MainComponentEntityOfCustomer {


  contractService:CustomerContractsService = inject(CustomerContractsService);

  constructor(){
    super();
    this.mapper = inject(CustomerContractsByCustomerTableMapperService);

  }

  override ngOnInit(): void {
    super.ngOnInit();
    this.title = 'Contratos';
    this.path = 'contrato';
    this.pathToOperations.push(
      {name:"Cadastrar novo Contrato",
       path: this.path + '/novo',
       title:"Novo " + this.path}
    )


  }

  openDialog(){

    let rota = '/cliente/' + this.selectedCustomer.cpfCnpj+ '/'+ this.title.toLowerCase();

    this.dialogService.openDialogPassingCustomerId(
      CustomerContractsDetailComponent,
      this.objectToEdit,
      this.selectedCustomer.cpfCnpj,
      rota,
      "800px"
      );
    this.objectToEdit = null;
  }






}
