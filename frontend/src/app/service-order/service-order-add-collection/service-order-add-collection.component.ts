import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { FormDetail } from 'src/app/shared/entities/FormDetail';
import { Component, Inject, OnInit, ViewChild, inject } from '@angular/core';
import { ServiceorderService } from '../services/serviceorder.service';
import { NgForm } from '@angular/forms';
import { ServiceOrderStatus } from 'src/app/shared/enums/ServiceOrderStatus';

@Component({
  selector: 'app-service-order-add-collection',
  templateUrl: './service-order-add-collection.component.html',
  styleUrls: ['./service-order-add-collection.component.css']
})
export class ServiceOrderAddCollectionComponent extends FormDetail implements OnInit {


  private serviceorderService:ServiceorderService = inject(ServiceorderService);
  @ViewChild('form') form:NgForm;

  constructor(
    public dialogRef: MatDialogRef<ServiceOrderAddCollectionComponent>,
    @Inject(MAT_DIALOG_DATA) public data:any
  ) { super();}



  ngOnInit(): void {
    this.onLoad(this.data);
  }

  override createObject() {
    let os = this.serviceorderService.list.find(os => os.id === this.idOfEditedItem);
    os.ammountCollected = this.form.value.quantity;
    os.serviceOrderStatus = ServiceOrderStatus.DONE;
    return os;
  }
  override save(object: any): void {
    
  }

  override destroy(): void {
    this.objectToEdit = null
    this.unsubscribeToObservables();
    this.dialogRef.close();
  }

}
