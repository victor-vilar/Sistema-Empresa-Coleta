import { EquipmentDetailComponent } from './equipment-detail/equipament-detail.component';
import { EquipmentsService } from 'src/app/equipaments/services/equipments.service';
import { Component, inject } from '@angular/core';
import { MainComponentEntity } from '../shared/entities/MainComponentEntity';


@Component({
  selector: 'app-equipaments',
  templateUrl: './equipaments.component.html',
  styleUrls: ['./equipaments.component.css']
})
export class EquipmentsComponent extends MainComponentEntity {

  equipmentService:EquipmentsService = inject(EquipmentsService);

  constructor() {
    super();

  }

  override ngOnInit() {
    super.ngOnInit();
    this.title='Equipamentos';
    this.path='equipamento';
    this.pathToOperations.push(
        {name:"Cadastrar novo Equipamento",
         path: this.path + '/novo'
        }
    )
  }

  openDialog(): void {
    this.dialogService.openDialog(EquipmentDetailComponent, this.objectToEdit,this.title.toLowerCase());
    this.objectToEdit = null;
  }

















}

