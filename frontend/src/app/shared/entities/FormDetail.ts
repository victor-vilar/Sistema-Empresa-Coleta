import { Subscription } from 'rxjs';
import { Inject, inject } from "@angular/core";
import { MAT_DIALOG_DATA } from "@angular/material/dialog";
import { ActivatedRoute, Router } from "@angular/router";
import { DialogServiceService } from "../services/dialog-service.service";
import { FormDetailHelperService } from '../services/form-detail-helper.service';

/**
 * Classe que sera utilizada pelos formulários de 'detalhes' (cadastro), formulários esse que
 * realizam operações do cadastro e atualização das entidades.
 */
export abstract class FormDetail{


  protected subscriptionsList:Subscription[] = [];
  protected activatedRoute:ActivatedRoute = inject(ActivatedRoute);
  protected router:Router = inject(Router);
  protected dialogService = inject(DialogServiceService);
  protected formDetailHelper = inject(FormDetailHelperService);
  protected clientCpfCnpj:string;
  protected objectToEdit:any;
  protected idOfEditedItem:number | string;
  protected crudOperation:string = "Cadastro";


  constructor(){ }


  //creates object that this detail form going to manipulate
  abstract createObject():any;

  //save method,
  abstract save(object:any):void;

  //close detail component
  abstract destroy():void;




  /**
   * Se for passado algum dado para o dialog, o formuário entrara em modo
   * edição, será salvo então o id para que seja chamado o metodo correto no service
   * Post = item sem id
   * Put  = item com id
  */
  protected onLoad(data:any):void{

    if(data.clientCpfCnpj !== undefined && data.clientCpfCnpj !== null){
      this.clientCpfCnpj = data.clientCpfCnpj;
    }

    //checado para saber se existe dados
    if(data.objectToEdit !== undefined && data.objectToEdit !== null){
      this.crudOperation="Atualização";
      this.objectToEdit = data.objectToEdit;


      //se existir dados, vai ser checado se o objeto passado possui um cpf/cnpj, que é o id
      //do objeto cliente. Se caso ele não tenha, simplesmente ira pegar o id do objeto passado
      //ja que todas as outras entidades possuem esse atributo
      if(data.objectToEdit.cpfCnpj !== undefined){
        this.idOfEditedItem = data.objectToEdit.cpfCnpj;
      }else{
        this.idOfEditedItem = this.objectToEdit.id;
      }

    }
  };


  /**
   * Metodo para desinscrever todos os observables do formulário
   */
  protected unsubscribeToObservables():void {
    this.subscriptionsList.forEach(s => s.unsubscribe());
  }








}

