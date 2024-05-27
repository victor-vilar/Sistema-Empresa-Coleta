import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DaysChipComponent } from './days-chip.component';

describe('DaysChipComponent', () => {
  let component: DaysChipComponent;
  let fixture: ComponentFixture<DaysChipComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DaysChipComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DaysChipComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
