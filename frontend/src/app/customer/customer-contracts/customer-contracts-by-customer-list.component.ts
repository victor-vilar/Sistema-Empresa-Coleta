import { CustomerContractsDetailComponent } from './customer-contracts-detail/customer-contracts-detail.component';
import { Component, inject } from '@angular/core';
import { CustomerContractsService } from 'src/app/customer/services/customerContracts.service';
import { CustomerContractsByCustomerTableMapperService } from './customer-contracts-by-customer-table-mapper.service';
import { CommunicationService } from 'src/app/shared/services/communication.service';
import { MainComponentEntityOfCustomer } from 'src/app/shared/entities/MainComponentEntityOfCustomer';

@Component({
  selector: 'app-customer-contracts-by-customer-list',
  templateUrl: './customer-contracts-by-customer-list.component.html',
  styleUrls: ['./customer-contracts-by-customer-list.component.css']
})
export class CustomerContractsByCustomerListComponent extends MainComponentEntityOfCustomer {


  contractService:CustomerContractsService = inject(CustomerContractsService);
  communicationService:CommunicationService = inject(CommunicationService);

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

    /**
     * Esse subscrição é para o serviço de communicação do applicação, para que eu consiga acessar o
     * contrato que foi clicado no componente de de contratos{ContractListComponent}.
     * Esse componente atual exibe somente os contratos de um cliente especifico, o componente ContractListComponent,
     * exibe o contratos de todos os clientes. Caso eu queira editar um contrato que foi visualizado no
     * ContractListComponent tenho que enviar para esse componente pois ele que contem o componente detail
     * de contratos no router.
     * E como foi feito tudo com dialog, precisei fazer dessa maneira.
     */
    this.subscriptions.push(
      this.communicationService.dataEmitter.subscribe(value => {
        this.objectToEdit = value
        this.openDialog();
      })
    );

  }

  openDialog(){

    let rota = '/cliente/' + this.selectedCustomer.cpfCnpj+ '/'+ this.title.toLowerCase();

    this.dialogService.openDialogPassingCustomerId(
      CustomerContractsDetailComponent,
      this.objectToEdit,
      this.selectedCustomer.cpfCnpj,
      rota,
      "100%",
      "100%");
    this.objectToEdit = null;
  }






}
