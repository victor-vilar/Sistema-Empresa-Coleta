import { Component, OnInit, inject } from "@angular/core";
import { ActivatedRoute } from "@angular/router";
import { Mapper } from "../interfaces/mapper.mapper";
import { DialogServiceService } from "../services/dialog-service.service";
import { MainComponentEntity } from "./MainComponentEntity";
import { CustomerService } from "src/app/customer/services/customer.service";
import { Customer } from "./Customer";

/**
 * Componente abstrato que sera herdado por todos os componentes que forem as paginas principais
 * dos componentes da aplicação. Essa classe reprensenta os componentes que manipulam entidades que são dos
 * clientes. Exemplo Contratos, Ordens de Serviço, Endereços
 */
@Component({
  template:''

})
export abstract class MainComponentEntityOfCustomer extends MainComponentEntity{

  selectedCustomer:Customer;
  customerService:CustomerService = inject(CustomerService);

  override ngOnInit(): void {
    super.ngOnInit();
    this.subscriptions.push(
      this.activatedRoute.paramMap.subscribe(param =>{
      this.selectedCustomer = this.customerService.list.find(obj => obj.cpfCnpj === param.get('cpfCnpj'));
      })
    )
  }



}
