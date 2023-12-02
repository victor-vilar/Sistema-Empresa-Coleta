import { Time } from '@angular/common';
import { Address } from './Address';
import { Customer } from './Customer';
import { ItemContract } from "./ItemContract";

export interface ServiceOrder {

  id:number,
  emissionDate:Date,
  serviceExpectedDate?:Date,
  vehicle:Vehicle,
  itemContract:ItemContract,
  customer:Customer,
  address:Address,
  ammountCollected:number,
  ineaManifest:string,
  serviceTime:Time,
  observation:string,
  osFileUrl:string


}
