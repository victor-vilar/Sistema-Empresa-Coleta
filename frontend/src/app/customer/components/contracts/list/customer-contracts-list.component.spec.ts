import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CustomerContractsListComponent } from './customer-contracts-list.component';

describe('CustomerContractsListTableComponent', () => {
  let component: CustomerContractsListComponent;
  let fixture: ComponentFixture<CustomerContractsListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CustomerContractsListComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CustomerContractsListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
