import jsPDF from 'jspdf';
import { CommunicationService } from '../../../shared/services/communication.service';
import { AfterViewInit, Component, DoCheck, ElementRef, OnInit, ViewChild, OnDestroy } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-pdf-template',
  templateUrl: './pdf-template.component.html',
  styleUrls: ['./pdf-template.component.css']
})
export class PdfTemplateComponent implements OnInit, AfterViewInit, OnDestroy {

  order:any;
  @ViewChild('html',{static:true}) html!:ElementRef;
  subscription:Subscription

  constructor(private CommunicationService:CommunicationService,
    private router:Router,
     ) {}



  ngOnInit(): void {
    this.subscription = this.CommunicationService.dataEmitter.subscribe(value => {
      this.order = value;
    })
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
          this.ngOnDestroy();
        },
        x:10,
        y:10
      })
    }

  }

}
