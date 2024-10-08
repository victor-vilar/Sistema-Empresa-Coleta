import { Component, inject } from '@angular/core';
import { CustomerService } from 'src/app/customer/services/customer.service';
import { CustomerSupervisorService } from 'src/app/customer/services/customer-supervisor.service';
import { CustomerSupervisorsDetailComponent } from '../detail/customer-supervisors-detail.component';
import { CustomerSupervisorsListTableComponentMapperService } from '../services/customer-supervisors-list-table-component-mapper.service';
import { MainComponentEntityOfCustomer } from 'src/app/shared/entities/MainComponentEntityOfCustomer';

@Component({
  selector: 'app-customer-supervisors-main',
  templateUrl: './customer-supervisors-main.component.html',
  styleUrls: ['./customer-supervisors-main.component.css']
})
export class CustomerSupervisorsMainComponent extends MainComponentEntityOfCustomer {

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
      rota,
    "800px",
  );

    this.objectToEdit = null;
  }




}
