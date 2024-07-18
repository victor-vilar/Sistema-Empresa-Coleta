import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CustomerMainComponent } from '../../customer-list/customer.component';

describe('ClientComponent', () => {
  let component: CustomerMainComponent;
  let fixture: ComponentFixture<CustomerMainComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CustomerMainComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CustomerMainComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
