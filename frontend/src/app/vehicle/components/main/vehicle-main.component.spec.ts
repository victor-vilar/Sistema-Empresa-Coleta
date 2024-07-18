import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VehicleMainComponent } from './vehicle-main.component';

describe('VehicleMainComponent', () => {
  let component: VehicleMainComponent;
  let fixture: ComponentFixture<VehicleMainComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ VehicleMainComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(VehicleMainComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
