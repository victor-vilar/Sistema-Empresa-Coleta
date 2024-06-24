import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { FormDetail } from 'src/app/shared/entities/FormDetail';
import { Component, Inject, Input, OnInit, ViewChild, inject } from '@angular/core';
import { ServiceorderService } from '../services/serviceorder.service';
import { NgForm } from '@angular/forms';
import { ServiceOrderStatus } from 'src/app/shared/enums/ServiceOrderStatus';
import { ServiceOrder } from 'src/app/shared/entities/ServiceOrder';

@Component({
  selector: 'app-service-order-add-collection',
  templateUrl: './service-order-add-collection.component.html',
  styleUrls: ['./service-order-add-collection.component.css']
})
export class ServiceOrderAddCollectionComponent implements OnInit {

  @Input()
  serviceorderService:ServiceorderService = inject(ServiceorderService);
  @Input()
  selectedServiceOrder:ServiceOrder;
  @ViewChild('form') form:NgForm;


  constructor() {}
    ngOnInit(): void {
  }


  createObject():any {
    return {
      id:this.selectedServiceOrder.id,
      amountCollected:Number(this.form.value.quantity),
      ineaManifest:'',
      osFileUrl:'',
      serviceTime:'',
      vechile:'teste',
    }
  }







}
