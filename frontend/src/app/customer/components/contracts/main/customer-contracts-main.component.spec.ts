import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CustomerContractsMainComponent } from './customer-contracts-main.component';

describe('CustomerContractsMainComponent', () => {
  let component: CustomerContractsMainComponent;
  let fixture: ComponentFixture<CustomerContractsMainComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CustomerContractsMainComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CustomerContractsMainComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
