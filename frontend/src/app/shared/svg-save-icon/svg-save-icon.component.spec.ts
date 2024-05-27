import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SvgSaveIconComponent } from './svg-save-icon.component';

describe('SvgSaveIconComponent', () => {
  let component: SvgSaveIconComponent;
  let fixture: ComponentFixture<SvgSaveIconComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SvgSaveIconComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SvgSaveIconComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
