import { DialogServiceService } from 'src/app/shared/services/dialog-service.service';
import { ResiduesService } from 'src/app/residue/services/residues.service';
import { Subscription } from 'rxjs';
import { ActivatedRoute } from '@angular/router';
import { ResidueDetailComponent } from '../detail/residue-detail.component';
import { Component, inject } from '@angular/core';
import { MainComponentEntity } from '../../../shared/entities/MainComponentEntity';
@Component({
  selector: 'app-residue-main',
  templateUrl: './residue-main.component.html',
  styleUrls: ['./residue-main.component.css']
})
export class ResidueMainComponent extends MainComponentEntity {

  residueService:ResiduesService = inject(ResiduesService);

  constructor() {
    super();
  }

  override ngOnInit(): void {
    super.ngOnInit()
    this.title='Residuos';
    this.path='residuo';
    this.pathToOperations.push(
        {name:"Cadastrar novo Resíduo",
         path: this.path + '/novo'},
    );
  }

  //open dialog of detail form
  openDialog(): void {
    this.dialogService.openDialog(ResidueDetailComponent, this.objectToEdit, this.title.toLowerCase());
    this.objectToEdit = null;
  }



}
