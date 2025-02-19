import { ComponentFixture, fakeAsync, TestBed, tick } from '@angular/core/testing';

import { DashboardComponent } from './dashboard.component';
import { Router } from '@angular/router';
import { CustomerContractsService } from 'src/app/customer/services/customer-contracts.service';
import { CustomerService } from 'src/app/customer/services/customer.service';
import { EquipmentsService } from 'src/app/equipments/services/equipments.service';
import { LoginService } from 'src/app/login/services/login.service';
import { ResiduesService } from 'src/app/residue/services/residues.service';
import { ServiceorderService } from 'src/app/service-order/services/serviceorder.service';
import { of } from 'rxjs';
import { By } from '@angular/platform-browser';
import { CurrencyPipe } from '@angular/common';
import { MockPipe } from 'src/app/tests/mock-pipe.pipe';
import { TestModule } from 'src/app/tests/tests.module';

fdescribe('DashboardComponent', () => {
  let component: DashboardComponent;
  let fixture: ComponentFixture<DashboardComponent>;
  let customerService:jasmine.SpyObj<CustomerService>;
  let contractService:jasmine.SpyObj<CustomerContractsService>;
  let equipmentService:jasmine.SpyObj<EquipmentsService>;
  let residueService:jasmine.SpyObj<ResiduesService>;
  let serviceOrderService:jasmine.SpyObj<ServiceorderService>;
  let router:jasmine.SpyObj<Router>;
  let loginService:jasmine.SpyObj<LoginService>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DashboardComponent ],
      providers:[
        {provide:CustomerService,useValue:jasmine.createSpyObj('CustomerService',['getCount'])},
        {provide:CustomerContractsService,useValue:jasmine.createSpyObj('CustomerContractsService',['getCount'])},
        {provide:EquipmentsService,useValue:jasmine.createSpyObj('EquipmentsService',['getCount'])},
        {provide:ResiduesService,useValue:jasmine.createSpyObj('ResiduesService',['getCount'])},
        {provide:ServiceorderService,useValue:jasmine.createSpyObj('ServiceorderService',['getCountOfNotExecuted'])},
        {provide:LoginService,useValue:jasmine.createSpyObj('LoginService',[''])},
        {provide:Router,useValue:jasmine.createSpyObj('Router',['navigate'])},
        {provide:CurrencyPipe, useValue:MockPipe}
      ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DashboardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();

    customerService = TestBed.inject(CustomerService) as jasmine.SpyObj<CustomerService>;
    customerService.getCount.and.returnValue(of(10));

    contractService = TestBed.inject(CustomerContractsService) as jasmine.SpyObj<CustomerContractsService>;
    contractService.getCount.and.returnValue(of(10));

    equipmentService = TestBed.inject(EquipmentsService) as jasmine.SpyObj<EquipmentsService>;
    equipmentService.getCount.and.returnValue(of(10));

    residueService = TestBed.inject(ResiduesService) as jasmine.SpyObj<ResiduesService>;
    residueService.getCount.and.returnValue(of(10));

    serviceOrderService = TestBed.inject(ServiceorderService) as jasmine.SpyObj<ServiceorderService>;
    serviceOrderService.getCountOfNotExecuted.and.returnValue(of(10));

    loginService = TestBed.inject(LoginService) as jasmine.SpyObj<LoginService>;
    loginService.applicationUser = 'fakeUser';

    router = TestBed.inject(Router) as jasmine.SpyObj<Router>;
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('Should navigate to login page if the user it is not logged',() => {

    loginService.applicationUser = undefined;
    expect(router.navigate).toHaveBeenCalled();

  });

  it('should update the itens count in the view',(fakeAsync(() =>{
    component.ngOnInit();
    tick(100);
    
    fixture.detectChanges();
    
    let appCardsComponents = fixture.debugElement.queryAll(By.css('app-card'));
    expect(appCardsComponents).toBeTruthy();
    appCardsComponents.forEach(c => expect(c.properties['mainInfo']).toBe(10));
    

  })))


});
