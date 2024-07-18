import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ItemContractListComponent } from './item-contract-list.component';

describe('ItensTableItemContractComponent', () => {
  let component: ItemContractListComponent;
  let fixture: ComponentFixture<ItemContractListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ItemContractListComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ItemContractListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
