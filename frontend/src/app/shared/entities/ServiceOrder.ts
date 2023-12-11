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
  vehicle?:Vehicle,
  itemContract:ItemContract,
  customer?:Customer,
  address:Address,
  ammountCollected?:number,
  ineaManifest?:string,
  serviceTime?:Time,
  observation?:string,
  osFileUrl?:string,
  serviceOrderStatus?:ServiceOrderStatusType,



}
