import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CustomerAddressesMainComponent } from './customer-addresses-main.component';

describe('CustomerAddressesMainComponent', () => {
  let component: CustomerAddressesMainComponent;
  let fixture: ComponentFixture<CustomerAddressesMainComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CustomerAddressesMainComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CustomerAddressesMainComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
