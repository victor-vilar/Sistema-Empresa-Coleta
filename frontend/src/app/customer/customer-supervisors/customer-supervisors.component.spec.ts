import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CustomerSupervisorsListComponent } from './customer-supervisors.component';

describe('CustomerSupervisorsListComponent', () => {
  let component: CustomerSupervisorsListComponent;
  let fixture: ComponentFixture<CustomerSupervisorsListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CustomerSupervisorsListComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CustomerSupervisorsListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
