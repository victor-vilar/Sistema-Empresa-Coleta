import { Component, OnInit } from '@angular/core';
import { ServiceorderService } from './services/serviceorder.service';

@Component({
  selector: 'app-serviceorder',
  templateUrl: './serviceorder.component.html',
  styleUrls: ['./serviceorder.component.css']
})
export class ServiceorderComponent implements OnInit {

  constructor(private serviceOrderService:ServiceorderService) { }

  ngOnInit(): void {

    

  }

}
