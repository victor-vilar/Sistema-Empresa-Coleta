import { CommunicationService } from 'src/app/shared/services/communication.service';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { Component, OnInit, Inject, OnDestroy, inject, } from '@angular/core';
import { ItemContract } from 'src/app/shared/entities/ItemContract';
import { MatTableDataSource } from '@angular/material/table';

import { ItensContractsDetailErrorsHelperService } from '../../services/itens-contracts-detail-errors-helper.service';
import { DialogServiceService } from 'src/app/shared/services/dialog-service.service';


@Component({
  selector: 'app-item-contract-list',
  templateUrl: './item-contract-list.component.html',
  styleUrls: ['./item-contract-list.component.css']
})

export class ItemContractListComponent implements OnInit, OnDestroy {


  constructor(public dialogRef: MatDialogRef<ItemContractListComponent>,@Inject(MAT_DIALOG_DATA) public data:any){}


  dataSource:MatTableDataSource<any>;
  tableData = [];
  deletedItens:Number[] = [];
  tableHeaders = []
  communicationService:CommunicationService = inject(CommunicationService);
  errorsHelper:ItensContractsDetailErrorsHelperService = inject(ItensContractsDetailErrorsHelperService);
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
