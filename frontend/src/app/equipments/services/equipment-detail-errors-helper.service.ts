import { ErrorsHelperService } from 'src/app/shared/services/erros-helper.service';
import { Injectable } from '@angular/core';
import { NgForm } from '@angular/forms';

@Injectable({
  providedIn: 'root'
})
export class EquipmentDetailErrorsHelperService extends ErrorsHelperService {

  private readonly INVALID_EQUIPMENT_NAME = 'O nome do não pode ser vazio!';
  private readonly INVALID_VOLUME_VALUE = 'O valor do volume do equipamento deve ser maior que zero';

  constructor() {
    super();
  }

  public checkErrors(form:NgForm,...vargs: any[]): void {
    this.checkIfEquipmentNameAreFilled(form,vargs[0]);
    this.checkIfVolumeInputIsNumber(form,vargs[1])
  }

  private checkIfEquipmentNameAreFilled(form:NgForm, isInvalidEquipmentName:boolean){
    if(!form.value.equipmentName.trim().length){
      isInvalidEquipmentName = true;
      this.dialogService.openErrorDialog(this.INVALID_EQUIPMENT_NAME);
      throw new Error(this.INVALID_EQUIPMENT_NAME);
    }
  }

  private checkIfVolumeInputIsNumber(form:NgForm, isInvalidVolume:boolean){
    if(isNaN(Number(form.value.equipmentSize)) || Number(form.value.equipmentSize) <= 0 || form.value.equipmentSize === '' ){
      isInvalidVolume=true;
      this.dialogService.openErrorDialog(this.INVALID_VOLUME_VALUE);
      throw new Error(this.INVALID_VOLUME_VALUE);
    }
  }


}
