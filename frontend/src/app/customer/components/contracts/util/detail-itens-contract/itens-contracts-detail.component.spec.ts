import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ItensContractsDetailComponent } from './itens-contracts-detail.component';

describe('CustomerContractsDetailItensComponent', () => {
  let component: ItensContractsDetailComponent;
  let fixture: ComponentFixture<ItensContractsDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ItensContractsDetailComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ItensContractsDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
