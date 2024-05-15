import { ServiceOrder } from '../../shared/entities/ServiceOrder';
import { Injectable } from '@angular/core';
import jsPDF from 'jspdf';


@Injectable({
  providedIn: 'root'
})
export class PdfBuilderService {

  //folha A4  = 210mm x 297mm (largura x altura)
  builder:jsPDF = new jsPDF({
    unit:'mm'
  });

  constructor() { }

  buildServiceOrderPdf(serviceOrder:ServiceOrder):void{

    let pdf = new jsPDF({unit:'mm'});
    this.header(pdf,serviceOrder);
    this.customer(pdf,serviceOrder);
    this.address(pdf,serviceOrder);
    window.open(URL.createObjectURL(pdf.output("blob")))


  }

  //header info
  private header(pdf:jsPDF, serviceOrder: ServiceOrder):void{
      pdf.context2d.lineWidth = 1
      pdf.context2d.strokeRect(2, 2, 206, 20);
      pdf.text("Ordem de Serviço",10,10);
      pdf.setFontSize(12);
      pdf.text(`Nº: ${serviceOrder.id}`,125,10);
      pdf.text(`Data de Emissão: ${serviceOrder.emissionDate}`,125,15);

  }

  //customer info
  private customer(pdf:jsPDF, serviceOrder:ServiceOrder):void{
    console.log('colocando customer')
    pdf.text('Dados do Cliente:',2,30);
    pdf.text('Nome:',2,36);
    pdf.text(serviceOrder.customer.nameCompanyName,20,36);

  }

  //address info
  private address(pdf:jsPDF, serviceOrder:ServiceOrder):void{
    let initialY = 41;
    let currentY = 41

    pdf.text('Local do Serviço:',2,currentY + 6);
    pdf.text('Logradouro:',2,currentY + 6);
    pdf.text(`${serviceOrder.address.addressName}`,45,41)
    pdf.text("Número:", 2 , currentY + 6)
    pdf.text(`${serviceOrder.address.addressNumber}`, 45 , 46)
    pdf.text("Complemento:", 2 , currentY + 6)
    pdf.text(`${serviceOrder.address.complement}`, 45 , 52)
    pdf.text("Cidade:", 2 , currentY + 6)
    pdf.text(`${serviceOrder.address.city}`, 45 , 58)
    pdf.text("Estado:", 2 , currentY + 6)
    pdf.text(`${serviceOrder.address.state}`, 45 , 64)


  }

  //item info
  private item(pdf:jsPDF, serviceOrder:ServiceOrder):void{}

  //oder info
  private order(pdf:jsPDF, serviceOrder:ServiceOrder):void{}



}
