import jsPDF from 'jspdf';
import { CommunicationService } from '../../shared/services/communication.service';
import { AfterViewInit, Component, DoCheck, ElementRef, OnInit, ViewChild, OnDestroy,inject, Inject } from '@angular/core';
import { Router, ActivatedRoute, ActivatedRouteSnapshot } from '@angular/router';
import { Subscription } from 'rxjs';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ActiveDataPoint } from 'chart.js';
import { ServiceorderService } from '../services/serviceorder.service';
import { CustomerService } from 'src/app/customer/services/customer.service';


@Component({
  selector: 'app-pdf-template',
  templateUrl: './pdf-template.component.html',
  styleUrls: ['./pdf-template.component.css']
})
export class PdfTemplateComponent implements OnInit, AfterViewInit, OnDestroy {

  order:any;
  customer:any;
  capitalizedCustomerName:string;
  @ViewChild('html',{static:true}) html!:ElementRef;
  subscription:Subscription = new Subscription();
  activatedRoute:ActivatedRoute = inject(ActivatedRoute);
  serviceOrderService:ServiceorderService = inject(ServiceorderService);
  customerService:CustomerService = inject(CustomerService);

  constructor(
    private router:Router,
    public dialogRef: MatDialogRef<PdfTemplateComponent>,
    @Inject(MAT_DIALOG_DATA) public data:any,
     ) {}



  ngOnInit(): void {
    this.onLoad();
  }

  onLoad(){
    console.log(this.data.objectToEdit);
   if(this.data.objectToEdit !== null && this.data.objectToEdit !== undefined){
       this.order = this.serviceOrderService.list.find(order => order.id === this.data.objectToEdit.id);
    }else{
       this.subscription.add(this.activatedRoute.paramMap.subscribe(param => {
       this.order = this.serviceOrderService.list.find(order => order.id === Number(param.get('id')))
       console.log(this.order);
       }))
    }
    this.customer = this.customerService.list.find(customer => customer.cpfCnpj === this.order.customerId);

  }

  ngOnDestroy(){
    this.subscription.unsubscribe();
    this.router.navigate(['./','ordem-servico']);
  }

  ngAfterViewInit(): void {
    if(this.html !== undefined){
      let doc = new jsPDF('p', 'pt', 'a4');
      doc.html(this.html.nativeElement,{
        callback: () => {
          window.open(URL.createObjectURL(doc.output("blob")))
          this.dialogRef.close();
        },
        x:20,
        y:20,
        autoPaging: 'text'
      })
    }

  }

}
