import { Injectable } from '@angular/core';
import jsPDF from 'jspdf';
import { ServiceOrder } from 'src/app/shared/entities/ServiceOrder';

@Injectable({
  providedIn: 'root'
})
export class PdfBuilderService {

  builder:jsPDF = new jsPDF();
  constructor() { }

  buildServiceOrderPdf(serviceOrder:ServiceOrder):void{



  }


}
