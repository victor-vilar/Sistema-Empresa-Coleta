import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ServiceorderMainComponent } from './serviceorder-main.component';

describe('ServiceorderMainComponent', () => {
  let component: ServiceorderMainComponent;
  let fixture: ComponentFixture<ServiceorderMainComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ServiceorderMainComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ServiceorderMainComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
