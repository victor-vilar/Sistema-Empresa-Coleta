import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ServiceorderListComponent } from './serviceorder-list.component';

describe('ServiceorderListComponent', () => {
  let component: ServiceorderListComponent;
  let fixture: ComponentFixture<ServiceorderListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ServiceorderListComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ServiceorderListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
