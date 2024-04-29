import { Component, inject } from '@angular/core';
import { CustomerService } from 'src/app/customer/services/customer.service';
import { CustomerSupervisorService } from 'src/app/customer/services/customerSupervisor.service';
import { CustomerSupervisorsDetailComponent } from './customer-supervisors-detail/customer-supervisors-detail.component';
import { CustomerSupervisorsListTableComponentMapperService } from './customer-supervisors-list-table-component-mapper.service';
import { MainComponentEntityOfCustomer } from 'src/app/shared/entities/MainComponentEntityOfCustomer';

@Component({
  selector: 'app-customer-supervisors',
  templateUrl: './customer-supervisors.component.html',
  styleUrls: ['./customer-supervisors.component.css']
})
export class CustomerSupervisorsListComponent extends MainComponentEntityOfCustomer {

  supervisorService:CustomerSupervisorService = inject(CustomerSupervisorService);

  constructor(){
  super();
  this.mapper = inject(CustomerSupervisorsListTableComponentMapperService);
  }

  override ngOnInit(): void {
    super.ngOnInit();
    this.title='Fiscais';
    this.path='fiscal';;
    this.pathToOperations.push(
      {name:"Cadastrar novo fiscal",
       path: this.path + '/novo',
      title:"Novo " + this.path}
    )
  }

  openDialog(){

    let rota = '/cliente/' + this.selectedCustomer.cpfCnpj+ '/'+ this.title.toLowerCase();

    this.dialogService.openDialogPassingCustomerId(CustomerSupervisorsDetailComponent,
      this.objectToEdit,
      this.selectedCustomer.cpfCnpj,
      rota);

    this.objectToEdit = null;
  }




}
