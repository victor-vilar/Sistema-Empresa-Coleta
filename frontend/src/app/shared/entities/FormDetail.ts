import { Subscription } from 'rxjs';
import { Inject, inject } from "@angular/core";
import { MAT_DIALOG_DATA } from "@angular/material/dialog";
import { ActivatedRoute, Router } from "@angular/router";
import { DialogServiceService } from "../services/dialog-service.service";

/**
 * Form detail class to be used in all the forms that make operations in the objects of the application.
 * Each form that make CRUD operations in the objects in the applicaiton, must inherance this class
 */
export abstract class FormDetail{


  protected subscriptionsList:Subscription[];
  protected activatedRoute:ActivatedRoute = inject(ActivatedRoute);
  protected router:Router = inject(Router);
  protected dialogService = inject(DialogServiceService);
  protected clientCpfCnpj:string;
  //id of the item that gonna be edited if the form is on edit mode
  protected idOfEditedItem:number | string;
  protected crudOperation:string;
  @Inject(MAT_DIALOG_DATA) protected data:any

  constructor(){ }




  //creates object that this detail form going to manipulate
  abstract createObject():any;

  //save method,
  abstract save(object:any):void;

  //method to configure the view if is on edit mode or not
  abstract onLoad():void;

  //close detail component
  abstract destroy():void;


  //TODO
    // method to unsubscribe the observablese of the form
  //abstract unsubscribeToObservables()
    // creates the observer of the cration
  //abstract sucess






}

