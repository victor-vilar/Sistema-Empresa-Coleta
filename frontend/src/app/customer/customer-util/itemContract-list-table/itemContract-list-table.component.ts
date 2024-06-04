import { CommunicationService } from './../../../shared/services/communication.service';
import { MAT_DIALOG_DATA, MatDialog, MatDialogRef } from '@angular/material/dialog';
import { Component, EventEmitter, Input, OnInit, Output, SimpleChanges, OnChanges, DoCheck, Inject, OnDestroy, inject, AfterViewChecked, AfterViewInit } from '@angular/core';
import { ItemContract } from 'src/app/shared/entities/ItemContract';
import { MatTableDataSource } from '@angular/material/table';

import { CustomerContractsDetailItensErrorsHelperService } from '../../customer-contracts/customer-contracts-detail/customer-contracts-detail-itens/customer-contracts-detail-itens-errors-helper.service';
import { DialogServiceService } from 'src/app/shared/services/dialog-service.service';


@Component({
  selector: 'app-itemContract-list-table',
  templateUrl: './itemContract-list-table.component.html',
  styleUrls: ['./itemContract-list-table.component.css']
})

export class ItemContractListTableComponent implements OnInit, OnDestroy {


  constructor(public dialogRef: MatDialogRef<ItemContractListTableComponent>,@Inject(MAT_DIALOG_DATA) public data:any){}


  dataSource:MatTableDataSource<any>;
  tableData = [];
  deletedItens:Number[] = [];
  tableHeaders = []
  communicationService:CommunicationService = inject(CommunicationService);
  errorsHelper:CustomerContractsDetailItensErrorsHelperService = inject(CustomerContractsDetailItensErrorsHelperService);
  dialogService:DialogServiceService = inject(DialogServiceService);


  /**
   * deletes itens from the list contract
   * @param item a item contract to delete
   */

  deleteItemFromList(item: ItemContract){

    this.dialogService.openConfirmCloseDialog("Deseja remover o Item ?").
    subscribe(response => {
      if(response){
        this.tableData = this.tableData.filter(e =>!this.errorsHelper.itemContractCompare(e, item));
        this.dataSource = new MatTableDataSource(this.tableData);


        //If the item has an id, there is the need to push it in the deleted itens list to this iten be deleted from the api after the contract update;
        if(item.id !== undefined || item.id !== null){
          this.deletedItens.push(item.id);
        }
      }
    })




  }

  ngOnInit(): void {
    this.tableHeaders=['No','Descricao','Residuo','Equipamento','Quantidade Equipamento','Quantidade','Valor','Opções'];
    this.tableData = this.data.objectToEdit;
    this.dataSource = new MatTableDataSource(this.tableData);
  }

  ngOnDestroy(): void {

    this.communicationService.sendData(
      {
        listOfItens:this.tableData,
        deletedItens:this.deletedItens
      }
    );
    console.log('destruindo')

  }

  destroy():void {
    this.dialogRef.close();
  }


}
