import { Injectable } from '@angular/core';
import { Bill } from 'src/app/shared/entities/Bill';
import { CrudBaseService } from 'src/app/shared/services/crudbase.service';

@Injectable({
  providedIn: 'root'
})
export class BillService extends CrudBaseService<Bill> {


  constructor() {
    super();
    this.rota ="bills";

   }
}
