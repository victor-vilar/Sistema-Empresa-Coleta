import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { FormDetail } from 'src/app/shared/entities/FormDetail';
import { Component, Inject, OnInit, ViewChild, inject } from '@angular/core';
import { ServiceorderService } from '../services/serviceorder.service';
import { NgForm } from '@angular/forms';
import { ServiceOrderStatus } from 'src/app/shared/enums/ServiceOrderStatus';
import { ServiceOrder } from 'src/app/shared/entities/ServiceOrder';

@Component({
  selector: 'app-service-order-add-collection',
  templateUrl: './service-order-add-collection.component.html',
  styleUrls: ['./service-order-add-collection.component.css']
})
export class ServiceOrderAddCollectionComponent extends FormDetail implements OnInit {



  private serviceorderService:ServiceorderService = inject(ServiceorderService);
  selectedServiceOrder:ServiceOrder;
  @ViewChild('form') form:NgForm;

  constructor(
    public dialogRef: MatDialogRef<ServiceOrderAddCollectionComponent>,
    @Inject(MAT_DIALOG_DATA) public data:any
  ) { super();}



  ngOnInit(): void {
    this.onLoad(this.data);
    console.log(this.data);
    this.selectedServiceOrder = this.serviceorderService.list.find(os => os.id === this.idOfEditedItem);
    console.log(this.selectedServiceOrder);

  }


  override createObject():any {
    let os = this.serviceorderService.list.find(os => os.id === this.idOfEditedItem);
    return {
      id:os.id,
      emissionDate:os.emissionDate,
      itemContract:os.itemContract.id,
      address:os.address.id,
      serviceExpectedDate:os.serviceExpectedDate,
      amountCollected:Number(this.form.value.quantity),
      serviceOrderStatus:ServiceOrderStatus.DONE
    }

  }
  override save(object: any): void {
    this.dialogService.openProgressDialog();
    let os =this.createObject()
    this.serviceorderService.update(os)
    .subscribe(this.saveObserver());
  }

  override destroy(): void {
    this.objectToEdit = null
    this.unsubscribeToObservables();
    this.dialogRef.close();
  }

  private saveObserver():any{
    return{
      next:(response) => {
        this.dialogService.closeProgressSpinnerDialog();
        this.dialogService.openSuccessDialogWithoutRedirect('Ordem salva com sucesso !');
        this.serviceorderService.getAll();
        this.destroy();
      },
      error:(error) => {
        this.dialogService.closeProgressSpinnerDialog();
        this.dialogService.openErrorDialog(error);
        console.log(error)
      }
    }
  }



}
