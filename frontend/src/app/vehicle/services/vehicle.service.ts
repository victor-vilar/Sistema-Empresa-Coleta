import { Injectable } from '@angular/core';
import { Vehicle } from 'src/app/shared/entities/Vehicle';
import { CrudBaseService } from 'src/app/shared/services/crudbase.service';

@Injectable({
  providedIn: 'root'
})
export class VehicleService extends CrudBaseService<Vehicle> {


  constructor() {
    super();
    this.rota = 'vehicles'
  }
}
