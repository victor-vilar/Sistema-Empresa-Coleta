import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SvgIconConfiguratorComponent } from './svg-list-icon.component';

describe('SvgIconConfiguratorComponent', () => {
  let component: SvgIconConfiguratorComponent;
  let fixture: ComponentFixture<SvgIconConfiguratorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SvgIconConfiguratorComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SvgIconConfiguratorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
