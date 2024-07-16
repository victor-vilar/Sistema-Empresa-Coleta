import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.css']
})
export class CardComponent implements OnInit {

  @Input()
  iconClass:string;

  @Input()
  text:string;

  @Input()
  mainInfo:any

  @Input()
  link:string

  constructor() { }

  ngOnInit(): void {
  }

}
