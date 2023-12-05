import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ServiceorderCreateComponent } from './serviceorder.create.component';

describe('CreateComponent', () => {
  let component: ServiceorderCreateComponent;
  let fixture: ComponentFixture<ServiceorderCreateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ServiceorderCreateComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ServiceorderCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
