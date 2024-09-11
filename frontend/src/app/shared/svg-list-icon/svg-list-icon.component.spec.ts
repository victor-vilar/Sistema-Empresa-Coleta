import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SvgListIconComponent } from './svg-list-icon.component';

describe('SvgIconConfiguratorComponent', () => {
  let component: SvgListIconComponent;
  let fixture: ComponentFixture<SvgListIconComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SvgListIconComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SvgListIconComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
