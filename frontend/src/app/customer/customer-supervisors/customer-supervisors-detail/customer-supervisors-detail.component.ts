import { CustomerSupervisorService } from 'src/app/customer/services/customerSupervisor.service';
import { Component, OnInit, ViewChild, AfterViewInit, Input, Inject } from '@angular/core';
import { NgForm } from '@angular/forms';
import { FormDetail } from 'src/app/shared/entities/FormDetail';
import { Supervisor } from 'src/app/shared/entities/Supervisor';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';


@Component({
  selector: 'app-customer-supervisors-detail',
  templateUrl: './customer-supervisors-detail.component.html',
  styleUrls: ['./customer-supervisors-detail.component.css']
})
export class CustomerSupervisorsDetailComponent extends FormDetail implements OnInit,AfterViewInit {

  constructor(
    private supervisorService:CustomerSupervisorService,
    private dialogRef: MatDialogRef<CustomerSupervisorsDetailComponent>,
    @Inject(MAT_DIALOG_DATA) public data:any,)
  {
    super();
  }

  @Input() isSubform:boolean=false;
  @ViewChild('form')form: NgForm;


  ngOnInit(): void {
    this.onLoad(this.data);
    this.crudOperation= 'Cadastro';
  }

  ngAfterViewInit(): void {
    setTimeout(() =>{
     this.form.setValue({
              supervisorName:this.objectToEdit.name,
              supervisorPhone: this.objectToEdit.phoneNumber,
              supervisorEmail:this.objectToEdit.email,
        })
    },100);
  }


  createObject():any {
    return {
      name:this.form.value.supervisorName,
      phoneNumber:this.form.value.supervisorPhone,
      email:this.form.value.supervisorEmail,
    }
  }


  save(): void {
    this.dialogService.openProgressDialog();
    let observable$;
    let supervisor = this.createObject();
    supervisor.customerId = this.clientCpfCnpj;

    if(this.objectToEdit === undefined){
      observable$ = this.supervisorService.save(supervisor);
    }else{
      supervisor.id = this.objectToEdit.id;
      observable$ = this.supervisorService.update(supervisor);
    }

    observable$.subscribe(this.saveObserver());
    this.destroy();
  }



  saveObserver(){
    return {
      next:(response) =>{
        this.dialogService.closeProgressSpinnerDialog();
        this.dialogService.openSucessDialog('Fiscal salvo com sucesso !','/clientes');
        this.supervisorService.getAll();
      },
      error:(response) =>{
        this.dialogService.closeProgressSpinnerDialog();
        this.dialogService.openErrorDialog('Ocorreu algum erro !');
        console.log(response);
      }
    }
  }


  destroy(): void {
    this.unsubscribeToObservables();
    this.dialogRef.close();

  }

  cleanForm(){
    this.form.reset();
  }

}


