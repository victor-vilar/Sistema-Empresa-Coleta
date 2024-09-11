import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ServiceorderDetailComponent } from './serviceorder.detail.component';

describe('CreateComponent', () => {
  let component: ServiceorderDetailComponent;
  let fixture: ComponentFixture<ServiceorderDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ServiceorderDetailComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ServiceorderDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
