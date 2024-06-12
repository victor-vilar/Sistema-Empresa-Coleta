import { Component, OnDestroy, OnInit, inject } from "@angular/core";
import { ActivatedRoute, Router } from "@angular/router";
import { Mapper } from "../interfaces/mapper.mapper";
import { DialogServiceService } from "../services/dialog-service.service";
import { Subscription } from "rxjs";

/**
 * Componente abstrato que sera herdado por todos os componentes que forem as paginas principais
 * dos componentes da aplicação.
 * Esses componente possuem um filho que é a tabela que mostra os itens salvos e possuem os formulario
 * de cadastro.
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
