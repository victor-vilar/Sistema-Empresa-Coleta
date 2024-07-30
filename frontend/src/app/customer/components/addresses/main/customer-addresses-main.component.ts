import { CustomerAddressService } from 'src/app/customer/services/customer-address.service';
import { Component, inject } from '@angular/core';
import { CustomerAddressesDetailComponent } from '../detail/customer-addresses-detail.component';
import { CustomerAddressListTableComponentMapperService } from '../services/customer-address-list-table-component-mapper-service.service';
import { MainComponentEntityOfCustomer } from 'src/app/shared/entities/MainComponentEntityOfCustomer';

@Component({
  selector: 'app-customer-addresses-main',
  templateUrl: './customer-addresses-main.component.html',
  styleUrls: ['./customer-addresses-main.component.css']
})
export class CustomerAddressesMainComponent extends MainComponentEntityOfCustomer {


  addressService:CustomerAddressService = inject(CustomerAddressService);

  constructor(){
    super();
    this.mapper = inject(CustomerAddressListTableComponentMapperService);
  }

  override ngOnInit(): void {
    super.ngOnInit();
    this.title = 'Enderecos';
    this.path = 'endereco';
    this.pathToOperations.push(
      {name:"Cadastrar novo Endereço",
       path: this.path + '/novo',
       title:"Novo " + this.path
      });
  }

  openDialog(){

    let rota = '/cliente/' + this.selectedCustomer.cpfCnpj+ '/'+ this.title.toLowerCase();

    this.dialogService.openDialogPassingCustomerId(
      CustomerAddressesDetailComponent,
      this.objectToEdit,
      this.selectedCustomer.cpfCnpj,
      rota,"800px","100%");

    this.objectToEdit = null;
  }



}
