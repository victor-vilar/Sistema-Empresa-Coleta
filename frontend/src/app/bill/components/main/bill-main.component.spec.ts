import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BillMainComponent } from '../bill.component';

describe('BillMainComponent', () => {
  let component: BillMainComponent;
  let fixture: ComponentFixture<BillMainComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BillMainComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BillMainComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
