import { ResiduesService } from 'src/app/residue/services/residues.service';
import { AfterViewInit, Component, Inject, OnInit, ViewChild, inject } from '@angular/core';
import { NgForm } from '@angular/forms';
import { FormDetail } from 'src/app/shared/entities/FormDetail';
import { ActivatedRoute, Router } from '@angular/router';
import { Residue } from 'src/app/shared/entities/Residue';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { DialogServiceService } from 'src/app/shared/services/dialog-service.service';
import { ErrorsHelperService } from 'src/app/shared/services/erros-helper.service';
import { ResidueDetailErrosHelperService } from '../../services/residue-detail-erros-helper.service';


@Component({
  selector: 'app-residue-detail',
  templateUrl: './residue-detail.component.html',
  styleUrls: ['./residue-detail.component.css']
})
export class ResidueDetailComponent extends FormDetail implements OnInit,AfterViewInit  {


  @ViewChild('singInForm') form: NgForm;
  isInvalidType = false;
  isInvalidDescription = false;


  private service:ResiduesService = inject(ResiduesService);
  private errorHelper:ErrorsHelperService = inject(ResidueDetailErrosHelperService);

  constructor(
    public dialogRef: MatDialogRef<ResidueDetailComponent>,
    @Inject(MAT_DIALOG_DATA) public data:any,
  ) { super();}


  createObject():any {
    return {
      id:this.idOfEditedItem,
      type:this.form.value.type,
      description:this.form.value.description
    }
  }

  ngOnInit(){
    this.onLoad(this.data);
  }

  ngAfterViewInit(): void {

    setTimeout(() =>{
      this.form.setValue({
        type:this.objectToEdit.type,
        description:this.objectToEdit.description,
      })
    },100);

  }


  save(): void {


    this.resetInvalidProperties();
    this.errorHelper.checkErrors(this.form,this.isInvalidType,this.isInvalidDescription);
    this.dialogService.openProgressDialog();
    //criando um novo objeto
    let observable$;
    let residue = this.createObject();
    //se for um objeto com id nulo, é um novo objeto
    //se não é atualização de um objeto existente.
    if(residue.id === undefined){
        observable$ = this.service.save(residue);
    }else{
        observable$ = this.service.update(residue);
    }
      observable$.subscribe(this.saveObjectObserver());

  }


  resetInvalidProperties(){
    this.isInvalidType = false;
    this.isInvalidDescription = false;
  }


  destroy(): void {
    this.objectToEdit =null
    this.unsubscribeToObservables();
    this.dialogRef.close();
  }

  cleanForm(){
    this.form.reset();
    this.resetInvalidProperties();
  }

  saveObjectObserver(){
    return{
      next:(response)=>{
        this.dialogService.closeProgressSpinnerDialog();
        this.dialogService.openSucessDialog('Resíduo salvo com sucesso !','residuos');
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
