import { Component, OnDestroy, OnInit, inject } from "@angular/core";
import { ActivatedRoute, Router } from "@angular/router";
import { Mapper } from "../interfaces/mapper.mapper";
import { DialogServiceService } from "../services/dialog-service.service";
import { Subscription } from "rxjs";
import { ResidueMainComponent } from "src/app/residue/components/main/residue-main.component";
import { ResidueListTableComponent } from "src/app/residue/components/list/residue-list-table.component";

/**
 * Componente abstrato que sera herdado por todos os componentes que
 * forem as 'páginas' principais dos componentes da aplicação.
 * 
 * Todo componente principal é somente uma página que contem outros
 * componentes para executar tarefas em uma entidade específica.
 * 
 * Por exemplo: o {@link ResidueMainComponent} que extende dessa classe,
 * possui um lista{@link ResidueListTableComponent} de resíduos cadastrados
 * e tambem possue outro componente que é um botão que chama o formulário
 * de cadastro de resíduos.
 * 
 */
@Component({
  template:''

})
export abstract class MainComponentEntity implements OnInit, OnDestroy{


  protected title:string;
  protected path:string;
  protected objectToEdit:any;
  protected pathToOperations:any = [];
  protected subscriptions:Subscription[] = [];
  protected mapper:Mapper;
  protected dialogService:DialogServiceService = inject(DialogServiceService);
  protected activatedRoute:ActivatedRoute = inject(ActivatedRoute);
  protected router:Router = inject(Router);


  ngOnInit(): void {
    this.subscriptions.push(
      this.activatedRoute.queryParams.subscribe(params => {
        if (params['dialog']) {
          this.openDialog();
        }
      })
    );
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(s => s.unsubscribe());
  }

  editObject(object:any){
    this.objectToEdit = object;
  }

  abstract openDialog():void;


}
