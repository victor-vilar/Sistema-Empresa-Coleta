import { DialogServiceService } from 'src/app/shared/services/dialog-service.service';
import { FormDetail } from 'src/app/shared/entities/FormDetail';
import { Equipment } from 'src/app/shared/entities/Equipment';
import { Component, OnInit, ViewChild, EventEmitter, Output, Inject, AfterViewInit, inject } from '@angular/core';
import { FormControl, FormGroupDirective, NgForm, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { EquipmentsService } from 'src/app/equipments/services/equipments.service';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ErrorStateMatcher } from '@angular/material/core';
import { EquipmentDetailErrorsHelperService } from '../../services/equipment-detail-errors-helper.service';
import { ErrorsHelperService } from 'src/app/shared/services/erros-helper.service';




// //myerror class to volumeSize input display error messages
// export class volumeErrorMatcher implements ErrorStateMatcher {
//   isErrorState(control: FormControl | null, form: FormGroupDirective | NgForm | null): boolean {
//     const isSubmitted = form && form.submitted;
//     if((isNaN(control.value) || control.value <= 0 || control.value === '') && (control.dirty || control.touched || isSubmitted) ){
//       return true;
//     }
//     return false;
//   }
// }


@Component({
  selector: 'app-equipament-new',
  templateUrl: './equipament-detail.component.html',
  styleUrls: ['./equipament-detail.component.css']
})
export class EquipmentDetailComponent extends FormDetail implements OnInit, AfterViewInit {

  @ViewChild('singInForm') form:NgForm;


  isInvalidEquipmentName:boolean = false;
  isInvalidVolume:boolean = false;

  private service:EquipmentsService = inject(EquipmentsService);
  private errorsHelper:ErrorsHelperService = inject(EquipmentDetailErrorsHelperService);

  constructor(
    public dialogRef: MatDialogRef<EquipmentDetailComponent>,
    @Inject(MAT_DIALOG_DATA) public data:any)
    {
      super();
    }

  createObject(): any {
    return {
      id:this.idOfEditedItem,
      equipmentName:this.form.value.equipmentName,
      sizeInMeterCubic:this.form.value.equipmentSize
    }
  }

  ngOnInit(): void {
    this.onLoad(this.data);
  }

  ngAfterViewInit(): void {
    setTimeout(() =>{
      this.form.setValue({
        equipmentName:this.objectToEdit.equipmentName,
        equipmentSize:this.objectToEdit.sizeInMeterCubic
      });
    },100);

  }

  resetInvalidProperties(){
    this.isInvalidVolume = false;
    this.isInvalidEquipmentName = false;
  }

  save(){

    this.resetInvalidProperties();
    this.errorsHelper.checkErrors(this.form,this.isInvalidEquipmentName,this.isInvalidVolume);

    this.dialogService.openProgressDialog();
    let observable$;
    let object = this.createObject();
    console.log(object)
    //se null object it is a new object
    //else it is a already exist one and it is a update
    if(object.id === undefined){
      observable$ = this.service.save(object);
    }else{
      observable$ = this.service.update(object);
    }

    observable$.subscribe(this.saveObjectObserver());

  }

  destroy(){
    this.objectToEdit = null
    this.unsubscribeToObservables();
    this.dialogRef.close();

  }

  cleanForm(){
    this.form.reset();
  }

  saveObjectObserver(){
    return{
      next:(response)=>{
        this.dialogService.closeProgressSpinnerDialog();
        this.dialogService.openSucessDialog('Equipamento salvo com sucesso !','equipamentos');
        this.service.getAll();
        this.destroy();
      },
      error:(response)=>{
        this.dialogService.closeProgressSpinnerDialog();
        this.dialogService.openErrorDialog('Ocorreu algum erro !');
        console.log(response);
      }
    }
  }


}
