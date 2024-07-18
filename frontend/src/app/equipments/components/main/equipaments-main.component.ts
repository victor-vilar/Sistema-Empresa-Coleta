import { EquipmentDetailComponent } from '../detail/equipament-detail.component';
import { EquipmentsService } from 'src/app/equipments/services/equipments.service';
import { Component, inject } from '@angular/core';
import { MainComponentEntity } from '../../../shared/entities/MainComponentEntity';


@Component({
  selector: 'app-equipaments-main',
  templateUrl: './equipaments-main.component.html',
  styleUrls: ['./equipaments-main.component.css']
})
export class EquipamentsMainComponent extends MainComponentEntity {

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

