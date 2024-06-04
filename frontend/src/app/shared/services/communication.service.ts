import { Injectable, EventEmitter, inject } from '@angular/core';
import { ActivatedRouteSnapshot } from '@angular/router';
import { Subject } from 'rxjs';

/**
 * service used to send objects to components that doesn't have a relationship
 */


@Injectable({
  providedIn: 'root'
})
export class CommunicationService {

  dataEmitter:Subject<any> = new Subject<any>();

  sendData(object:any){

    console.log('Sending Object:');
    console.log(object);
    this.dataEmitter.next(object);

  }

  constructor() { }
}
