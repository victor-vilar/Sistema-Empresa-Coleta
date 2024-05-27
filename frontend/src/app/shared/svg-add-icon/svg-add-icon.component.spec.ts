import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SvgAddIconComponent } from './svg-add-icon.component';

describe('SvgAddIconComponent', () => {
  let component: SvgAddIconComponent;
  let fixture: ComponentFixture<SvgAddIconComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SvgAddIconComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SvgAddIconComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
