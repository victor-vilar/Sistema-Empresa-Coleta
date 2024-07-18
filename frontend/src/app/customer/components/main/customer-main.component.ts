import { CustomerDetailComponent } from '../detail/customer-detail.component';
import { Component } from '@angular/core';
import { MainComponentEntityOfCustomer } from 'src/app/shared/entities/MainComponentEntityOfCustomer';

@Component({
  selector: 'app-client',
  templateUrl: './customer-main.component.html',
  styleUrls: ['./customer-main.component.css']
})
export class CustomerMainComponent extends MainComponentEntityOfCustomer {


  constructor(){
    super();
  }

  override ngOnInit(): void {
    super.ngOnInit();
    this.title='Clientes';
    this.path='cliente';
    this.pathToOperations = [
        {name:"Cadastrar novo Cliente ",
         path: this.path + '/novo'},
      ];
  }

  //open dialog of detail form
  openDialog(): void {
    this.dialogService.openDialog(CustomerDetailComponent, this.objectToEdit, this.title.toLowerCase());
    this.objectToEdit = null;
  }

}
