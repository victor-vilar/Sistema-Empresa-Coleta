import { MeasurementUnit } from '../../../../shared/enums/MeasurementUnit';
import { CollectionFrequency } from '../../../../shared/entities/CollectionFrequency';
import { WeekDay } from '@angular/common';
import { DialogServiceService } from '../../../../shared/services/dialog-service.service';
import { AfterViewInit, Component, EventEmitter, Input, OnChanges, OnDestroy, OnInit, Output, SimpleChanges, ViewChild, inject } from '@angular/core';
import { NgForm } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { EquipmentsService } from 'src/app/equipaments/services/equipments.service';
import { ResiduesService } from 'src/app/residue/services/residues.service';
import { Equipment } from 'src/app/shared/entities/Equipment';
import { ItemContract } from 'src/app/shared/entities/ItemContract';
import { Residue } from 'src/app/shared/entities/Residue';
import { Schedule, getScheduleValues } from 'src/app/shared/enums/Schedule';
import { Weekday, WeekdayType, getWeekdayValues } from 'src/app/shared/enums/Weekday';
import { getMeasurementUnitValues } from 'src/app/shared/enums/MeasurementUnit';
import { CustomerContractsDetailItensErrorsHelperService } from './customer-contracts-detail-itens-errors-helper.service';
import { ErrorsHelperService } from 'src/app/shared/services/erros-helper.service';
import { Subscription } from 'rxjs';
import { ItemContractListTableComponent } from 'src/app/customer/customer-util/itemContract-list-table/itemContract-list-table.component';
import { CommunicationService } from 'src/app/shared/services/communication.service';

@Component({
  selector: 'app-customer-contracts-detail-itens',
  templateUrl: './customer-contracts-detail-itens.component.html',
  styleUrls: ['./customer-contracts-detail-itens.component.css']
})
export class CustomerContractsDetailItensComponent implements OnInit, OnChanges, AfterViewInit, OnDestroy {

    //form
    @ViewChild('form') form:NgForm;
    @ViewChild('daysInput') daysInput:HTMLSelectElement

    weekdayButtonDisabled:boolean = true;
    @Input()itemContractList:any[];
    @Input()deletedSavedItensIdList:number[];


    residuesService:ResiduesService = inject(ResiduesService);
    equipmentsService:EquipmentsService = inject(EquipmentsService);;
    dialogService:DialogServiceService = inject(DialogServiceService);
    errorsHelper:CustomerContractsDetailItensErrorsHelperService = inject (CustomerContractsDetailItensErrorsHelperService);
    communicationService:CommunicationService = inject(CommunicationService);

    residuesList:Residue[];
    equipmentsList:Equipment[];
    weekdaysListToAddToItemContract:WeekdayType[] = [];
    subscriptions:Subscription = new Subscription();
    //sum of itens of contract
    totalValueOfContract:number = 0;
    listSize:number;
    //enum values to fill the select options components
    scheduleEnumValues = getScheduleValues();
    weekDaysEnumValues = getWeekdayValues();
    measurementUnitList = getMeasurementUnitValues();

    //constantes para as mensagens de salvamento
    private readonly SAVE_MESSAGE = {header:"Cadastro",message:"Resíduo inserido com sucesso"};
    private readonly NEW_WEEKDAY_MESSAGE = {header:"Dia Adicionado",message:"Dia inserido com sucesso"};


  constructor() {}

  ngOnDestroy(): void {
    this.subscriptions.unsubscribe();
  }



  ngAfterViewInit(): void {
    setTimeout(() =>{
      this.listSize = this.itemContractList.length;
    },250)
  }


  ngOnInit(): void {

        //subscribing to equipament service and residue service
        this.subscriptions.add(this.equipmentsService.refreshAllData().subscribe(e =>{
          this.equipmentsList = e;
        }))

        this.subscriptions.add(this.residuesService.refreshAllData().subscribe(e => {
          this.residuesList = e;
        }))

        //subscription when an item is deleted from the itemContractList in the itemContractListTable component
        this.subscriptions.add(this.communicationService.dataEmitter.subscribe(obj => {
          this.updateItensList(obj);
        }))

  }

    //execute services methods to get all info from api
    ngOnChanges(changes: SimpleChanges): void {
      this.getAll();
      //maping itens from contract
      if(this.itemContractList.length > 0){
        this.itemContractList = this.itemContractListFromApiMapper();
        //updating view
        this.sumTotalOfContract();
      }

    }

    //creates an itemContract from form fields
    createItemContractObject():ItemContract{
      return {
        residue:this.residuesService.list.find(e => e.id === Number(this.form.value.residue)),
        equipment:this.equipmentsService.list.find(e => e.id === Number(this.form.value.equipment)),
        equipmentQuantity:Number(this.form.value.equipmentQuantity),
        qtdOfResidue:Number(this.form.value.quantity),
        itemValue:Number(this.form.value.itemValue),
        description:this.form.value.description,
        collectionFrequency:this.createCollectionFrequency(),
        measurementUnit:this.form.value.measurementUnit
      }
    }

      //create collection frequency
    createCollectionFrequency():CollectionFrequency{
      return {
        days:this.weekdaysListToAddToItemContract,
        schedule:this.form.value.schedule
      }
    }

    /**
     * update the list of the services
     */
    getAll(){
      this.equipmentsService.getAll();
      this.residuesService.getAll();
    }


    //add an item to contract
    addItemToContract(){

      let itemContract = this.createItemContractObject();
      this.errorsHelper.checkErrors(this.form,this.itemContractList,itemContract);

      //push item to list
      this.itemContractList.push(itemContract);

      //updating view of total value
      this.sumTotalOfContract();

      //clearing fields to add new itens
      this.form.reset()
      this.clearWeekdayList();


      //angular material snack bar message
      this.dialogService.openSnackBar(this.SAVE_MESSAGE.message,this.SAVE_MESSAGE.header);

      this.listSize = this.itemContractList.length;

    }



    /**
     * display the total contract price
     */
    sumTotalOfContract(){
      this.totalValueOfContract = 0;
      this.itemContractList.forEach(e =>{
        this.totalValueOfContract += e.itemValue * e.qtdOfResidue;
      })
    }

    //transform the item contract list in a form that api could save the itens
    //Is send only the id of equipment and residue
    itemContractListMapper(){
      return this.itemContractList.map(e =>{
        return {
          id:e.id,
          residue:e.residue.id,
          equipment:e.equipment.id,
          equipmentQuantity:e.equipmentQuantity,
          qtdOfResidue:e.qtdOfResidue,
          itemValue:e.itemValue,
          description:e.description,
          collectionFrequency: e.collectionFrequency,
          measurementUnit:e.measurementUnit
        }
      })
    }

    //transform list of itens from api to itemContract of front
    itemContractListFromApiMapper(){

        return this.itemContractList.map(e =>{

          let residue = this.residuesService.list.find(r => r.type === e.residue);
          let equipment = this.equipmentsService.list.find(eq => eq.equipmentName === e.equipment);

          return {
            id:e.id,
            residue:residue,
            equipment:equipment,
            equipmentQuantity:e.equipmentQuantity,
            qtdOfResidue:e.qtdOfResidue,
            itemValue:e.itemValue,
            description:e.description,
            collectionFrequency: e.collectionFrequency,
            measurementUnit:e.measurementUnit
          }
        })
    }


    //Update the itens list of this component, getting the list that comes from the itemContract table dialog component.
    //
    updateItensList(listsHolder:any){

      this.itemContractList = listsHolder.listOfItens;

      if(listsHolder.deletedItens.length > 0){
        listsHolder.deletedItens.forEach(e => {
          this.deletedSavedItensIdList.push(e);
        })
      }



      //refresh total value
      this.listSize = this.itemContractList.length;
      this.sumTotalOfContract();
    }

    /**
     * Ao adicionar um item, deve ser escolhido um dia da semana em que o serviço será executado pelo menos.
     * Esse metodo serve somente para impedir que dois dias da semana iguais sejam inseridos na lista de dias.
     * Exemplo: [SEGUNDA, SEGUNDA] < -- ERRADO.
     */
    addNewWeekday(){

      if(!this.weekdaysListToAddToItemContract.find(e => e ===this.form.value.days) && this.form.value.days !== ""){


        this.weekdaysListToAddToItemContract.push(this.form.value.days);
        this.dialogService.openSnackBar(this.NEW_WEEKDAY_MESSAGE.message,this.NEW_WEEKDAY_MESSAGE.header);

      }

      this.daysInput.value = "";
      this.disableButton();


    }

  /**
   * remove a weekday from list
   */
    removeWeekday(day:WeekdayType){
      this.weekdaysListToAddToItemContract = this.weekdaysListToAddToItemContract.filter(e => e !== day);
    }

    /**
     * clear all the list of weekday, used after the itens it is crated and added into the itens list
     */
    clearWeekdayList(){
      this.weekdaysListToAddToItemContract =[];
    }

    /**
     * if the days field is empty or the day is already in the list the button will be disabled
     */
    disableButton(){
      if(this.form.value.days === "" || this.weekdaysListToAddToItemContract.some(e => e === this.form.value.days)){
        this.weekdayButtonDisabled = true;

      }else{
        this.weekdayButtonDisabled = false;

      }
    }

    /**
     * Open the dialog view of the itens of the added to itemContractList
     */
    openItensView(){
      this.dialogService.openDialog(ItemContractListTableComponent,this.itemContractList,null,"800px");
    }


}


