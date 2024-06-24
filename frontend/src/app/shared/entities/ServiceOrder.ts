import { Time } from '@angular/common';
import { Address } from './Address';
import { Customer } from './Customer';
import { ItemContract } from "./ItemContract";
import { Vehicle } from "./Vehicle";
import { ServiceOrderStatusType } from '../enums/ServiceOrderStatus';

export interface ServiceOrder {

  id?:number,
  emissionDate?:Date,
  serviceExpectedDate:Date,
  serviceExecutedDate?:Date,
  vehicle?:Vehicle,
  itemContract:ItemContract,
  customerId?:String,
  address:Address,
  amountCollected?:number,
  ineaManifest?:string,
  serviceTime?:Time,
  observation?:string,
  osFileUrl?:string,
  serviceOrderStatus?:ServiceOrderStatusType,



}
