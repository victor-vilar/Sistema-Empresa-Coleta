import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ResidueMainComponent } from './residue-main.component';

describe('ResidueMainComponent', () => {
  let component: ResidueMainComponent;
  let fixture: ComponentFixture<ResidueMainComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ResidueMainComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ResidueMainComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
