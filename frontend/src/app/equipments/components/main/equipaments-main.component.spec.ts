import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EquipamentsMainComponent } from './equipaments-main.component';

describe('EquipamentsComponent', () => {
  let component: EquipamentsMainComponent;
  let fixture: ComponentFixture<EquipamentsMainComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EquipamentsMainComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EquipamentsMainComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
