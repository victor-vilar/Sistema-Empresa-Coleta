import { DialogServiceService } from 'src/app/shared/services/dialog-service.service';
import { ActivatedRoute } from '@angular/router';
import { CustomerAddressService } from 'src/app/customer/services/customerAddress.service';
import { Component, OnInit, inject } from '@angular/core';
import { CustomerService } from 'src/app/customer/services/customer.service';
import { Customer } from 'src/app/shared/entities/Customer';
import { CustomerAddressesDetailComponent } from './customer-addresses-detail/customer-addresses-detail.component';
import { CustomerAddressListTableComponentMapperService } from './customer-address-list-table-component-mapper-service.service';
import { Mapper } from 'src/app/shared/interfaces/mapper.mapper';

@Component({
  selector: 'app-customer-addresses',
  templateUrl: './customer-addresses.component.html',
  styleUrls: ['./customer-addresses.component.css']
})
export class CustomerAddressesListComponent implements OnInit {

  objectToEdit = "";
  selectedCustomer:Customer;
  title='Enderecos'
  pathPrefix='endereco';
  pathToOperations = [{name:"Cadastrar novo Endereço", path: this.pathPrefix + '/novo', title:"Novo " + this.pathPrefix}];
  customerService:CustomerService = inject(CustomerService);
  addressService:CustomerAddressService = inject(CustomerAddressService);
  mapper:Mapper = inject(CustomerAddressListTableComponentMapperService);
  dialogService:DialogServiceService = inject(DialogServiceService);
  route:ActivatedRoute = inject(ActivatedRoute);

  constructor(){}



  ngOnInit(): void {

    this.route.queryParams.subscribe(params => {
      if (params['dialog']) {
        this.openDialog();
      }
    });




    this.route.paramMap.subscribe(param =>{
      this.selectedCustomer = this.customerService.list.find(obj => obj.cpfCnpj === param.get('cpfCnpj'));
    })


  }

  openDialog(){

    let rota = '/cliente/' + this.selectedCustomer.cpfCnpj+ '/'+ this.title.toLowerCase();

    this.dialogService.openDialogPassingCustomerId(CustomerAddressesDetailComponent,
      this.objectToEdit,
      this.selectedCustomer.cpfCnpj,
      rota);

    this.objectToEdit = null;
  }

  editObject(object:any){
    this.objectToEdit = object;
  }


}
