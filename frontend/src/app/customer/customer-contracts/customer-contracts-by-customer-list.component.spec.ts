import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CustomerContractsByCustomerListComponent } from './customer-contracts-by-customer-list.component';

describe('CustomerContractsComponent', () => {
  let component: CustomerContractsByCustomerListComponent;
  let fixture: ComponentFixture<CustomerContractsByCustomerListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CustomerContractsByCustomerListComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CustomerContractsByCustomerListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
