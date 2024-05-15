import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ServiceOrderAddCollectionComponent } from './service-order-add-collection.component';

describe('ServiceOrderAddCollectionComponent', () => {
  let component: ServiceOrderAddCollectionComponent;
  let fixture: ComponentFixture<ServiceOrderAddCollectionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ServiceOrderAddCollectionComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ServiceOrderAddCollectionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
