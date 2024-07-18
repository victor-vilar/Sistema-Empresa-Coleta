import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CustomerAddressesListComponent } from './customer-addresses-list.component';

describe('CustomerAddressListTableComponent', () => {
  let component: CustomerAddressesListComponent;
  let fixture: ComponentFixture<CustomerAddressesListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CustomerAddressesListComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CustomerAddressesListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
