import { ServiceorderService } from './../../service-order/services/serviceorder.service';
import { Subscription } from 'rxjs';
import { ResiduesService } from '../../residue/services/residues.service';
import { EquipmentsService } from '../../equipaments/services/equipments.service';
import { CustomerContractsService } from '../../customer/services/customerContracts.service';
import { CustomerService } from '../../customer/services/customer.service';

import { AfterViewInit, Component, ElementRef, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/login/services/login.service';
import { Chart } from 'chart.js/auto';


@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit, OnDestroy, AfterViewInit {


  customerRegisteredLength;
  contractRegisteredLength;
  equipmentRegisteredLength;
  residueRegisteredLength;
  serviceorderRegisteredLength;
  totalValue = 0;
  subscriptionList:Subscription[] = [];
  chart: any = [];
  days:any = [];


  constructor(
    private customerService:CustomerService,
    private contractService:CustomerContractsService,
    private equipmentService:EquipmentsService,
    private residueService:ResiduesService,
    private serviceOrderService:ServiceorderService,
    private router:Router,
    private loginService:LoginService) { }



  ngOnDestroy(): void {
      this.subscriptionList.forEach(e => e.unsubscribe());
  }

  ngOnInit(): void {


  if(this.loginService.applicationUser === undefined){
        this.router.navigate(['/login'])
  }

  this.subscriptionList
  .push(
    this.customerService.refreshAllData()
    .subscribe(response =>{this.customerRegisteredLength = response.length})
    );

  this.subscriptionList
  .push(
    this.contractService.refreshAllData().subscribe(response =>{
        this.contractRegisteredLength = response.length

        //return a list of lists of itens
        let listOfItens = response.map(e => e.itens);
        listOfItens.forEach(e =>
          //loop trough each item to sum all contracts
          e.forEach(c => this.totalValue += c.itemValue * c.qtdOfResidue));
  }));

  this.subscriptionList
  .push(
    this.equipmentService.refreshAllData()
    .subscribe(response =>{this.equipmentRegisteredLength = response.length})
  );


  this.subscriptionList
  .push(
    this.residueService.refreshAllData()
    .subscribe(response =>{this.residueRegisteredLength = response.length})
  );

  this.subscriptionList
  .push(
    this.serviceOrderService.refreshAllData()
    .subscribe(response =>{
      this.serviceorderRegisteredLength = response.length
      this.fillDays();
    })
  );


  this.customerService.getAll();
  this.contractService.getAll();
  this.equipmentService.getAll();
  this.residueService.getAll();
  this.serviceOrderService.getAll();
  }

  ngAfterViewInit(): void {
    this.createNextServiceDaysChart();
  }


  createNextServiceDaysChart(){
    this.chart = new Chart(
      'orders',
    {
      type: 'bar',
      data: {
        labels: this.days,
        datasets: [
          {
            label: 'Serviços por dia',
            data: ['11','22','33'],
            borderWidth:1,
          }
        ]
      }
    }
  );
  }


  fillDays(){

    let date = new Date();
    let newDate = new Date();
    for(let i = 0; i < 10 ; i++){
      newDate.setDate(date.getDate() + i)  ;
      this.days.push(newDate.toLocaleDateString())
      newDate = new Date();
    }

    console.log(this.days);
  }


}




