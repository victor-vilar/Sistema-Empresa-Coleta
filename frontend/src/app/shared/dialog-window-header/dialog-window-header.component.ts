import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-dialog-window-header',
  templateUrl: './dialog-window-header.component.html',
  styleUrls: ['./dialog-window-header.component.css']
})
export class DialogWindowHeaderComponent implements OnInit {

  @Input()
  title:string;

  @Output()
  closeDialogEmitter:EventEmitter<boolean> = new EventEmitter<boolean>();

  public sendCloseDialogSignal(){
    this.closeDialogEmitter.emit(true);
  }


  constructor() { }

  ngOnInit(): void {
  }

}
