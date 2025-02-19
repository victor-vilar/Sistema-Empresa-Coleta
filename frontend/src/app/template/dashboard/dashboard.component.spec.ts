import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DashboardComponent } from './dashboard.component';
import { Router } from '@angular/router';
import { CustomerContractsService } from 'src/app/customer/services/customer-contracts.service';
import { CustomerService } from 'src/app/customer/services/customer.service';
import { EquipmentsService } from 'src/app/equipments/services/equipments.service';
import { LoginService } from 'src/app/login/services/login.service';
import { ResiduesService } from 'src/app/residue/services/residues.service';
import { ServiceorderService } from 'src/app/service-order/services/serviceorder.service';

describe('DashboardComponent', () => {
  let component: DashboardComponent;
  let fixture: ComponentFixture<DashboardComponent>;
  let customerService:CustomerService;
  let contractService:CustomerContractsService;
  let equipmentService:EquipmentsService;
  let residueService:ResiduesService;
  let serviceOrderService:ServiceorderService;
  let router:Router;
  let loginService:LoginService;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DashboardComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DashboardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
