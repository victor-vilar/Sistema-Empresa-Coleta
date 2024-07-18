import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CustomerSupervisorsMainComponent } from './customer-supervisors-main.component';

describe('CustomerSupervisorsListComponent', () => {
  let component: CustomerSupervisorsMainComponent;
  let fixture: ComponentFixture<CustomerSupervisorsMainComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CustomerSupervisorsMainComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CustomerSupervisorsMainComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
