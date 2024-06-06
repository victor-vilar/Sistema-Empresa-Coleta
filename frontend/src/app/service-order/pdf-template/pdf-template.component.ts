import jsPDF from 'jspdf';
import { CommunicationService } from '../../shared/services/communication.service';
import { AfterViewInit, Component, DoCheck, ElementRef, OnInit, ViewChild, OnDestroy,inject, Inject } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';


@Component({
  selector: 'app-pdf-template',
  templateUrl: './pdf-template.component.html',
  styleUrls: ['./pdf-template.component.css']
})
export class PdfTemplateComponent implements OnInit, AfterViewInit, OnDestroy {

  order:any;
  @ViewChild('html',{static:true}) html!:ElementRef;
  subscription:Subscription
  CommunicationService:CommunicationService = inject(CommunicationService);
  constructor(
    private router:Router,
    public dialogRef: MatDialogRef<PdfTemplateComponent>,
    @Inject(MAT_DIALOG_DATA) public data:any,
     ) {}



  ngOnInit(): void {
    this.onLoad();
  }

  onLoad(){
    this.order = this.data.objectToEdit;
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
        x:10,
        y:10
      })
    }

  }

}
