import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ContractsMainComponent } from './contracts-main.component';

describe('CustomerContractsListComponent', () => {
  let component: ContractsMainComponent;
  let fixture: ComponentFixture<ContractsMainComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ContractsMainComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ContractsMainComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
