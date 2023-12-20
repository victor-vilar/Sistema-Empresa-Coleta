import jsPDF from 'jspdf';
import { CommunicationService } from './../../shared/services/communication.service';
import { AfterViewInit, Component, DoCheck, ElementRef, OnInit, ViewChild } from '@angular/core';

@Component({
  selector: 'app-pdf-template',
  templateUrl: './pdf-template.component.html',
  styleUrls: ['./pdf-template.component.css']
})
export class PdfTemplateComponent implements OnInit, AfterViewInit {

  order:any;
  @ViewChild('html',{static:true}) html!:ElementRef;


  constructor(private CommunicationService:CommunicationService ) {}



  ngOnInit(): void {

    this.CommunicationService.dataEmitter.subscribe(value => {
      this.order = value;
    })
  }

  ngAfterViewInit(): void {
    if(this.html !== undefined){
      let doc = new jsPDF('p', 'pt', 'a4');
      doc.html(this.html.nativeElement,{
        callback: () => {
          window.open(URL.createObjectURL(doc.output("blob")))

        },
        x:10,
        y:10
      })
    }

  }

}
