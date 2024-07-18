import { Component} from '@angular/core';
import { ItensTableComponent } from 'src/app/shared/itens-table/itens-table.component';


@Component({
  selector: 'app-customer-addresses-list',
  templateUrl: './customer-addresses-list.component.html',
  styleUrls: ['./customer-addresses-list.component.css']
})
export class CustomerAddressesListComponent extends ItensTableComponent {

  constructor(){
    super()
    this.tableHeaders = ['Logradouro','Numero','Complemento','CEP','Cidade','Estado','Existe-Coleta','Opções'];
  }




}
