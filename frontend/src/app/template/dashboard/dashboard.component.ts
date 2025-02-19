import { ServiceorderService } from './../../service-order/services/serviceorder.service';
import { Observable, Subscription } from 'rxjs';
import { ResiduesService } from '../../residue/services/residues.service';
import { EquipmentsService } from '../../equipments/services/equipments.service';
import { CustomerContractsService } from '../../customer/services/customer-contracts.service';
import { CustomerService } from '../../customer/services/customer.service';

import { AfterContentInit, AfterViewInit, Component, ElementRef, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/login/services/login.service';
import { Chart } from 'chart.js/auto';


@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit, OnDestroy, AfterContentInit {


  $customerTotalCount: Observable<number>;
  $contractTotalCount: Observable<number>;
  $equipmentTotalCount: Observable<number>;
  $residueTotalCount: Observable<number>;
  $serviceOrderTotalCount: Observable<number>;
  totalValue = 10;
  subscriptions:Subscription = new Subscription();
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
      this.subscriptions.unsubscribe();
  }

  ngOnInit(): void {

  if(this.loginService.applicationUser === undefined){
        this.router.navigate(['/login'])
  }


  this.$customerTotalCount = this.customerService.getCount();
  this.$contractTotalCount = this.contractService.getCount();
  this.$equipmentTotalCount = this.equipmentService.getCount();
  this.$residueTotalCount = this.residueService.getCount();
  this.$serviceOrderTotalCount = this.serviceOrderService.getCountOfNotExecuted();

  }

  ngAfterContentInit(): void {
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




