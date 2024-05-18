import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DialogWindowHeaderComponent } from './dialog-window-header.component';

describe('DialogWindowHeaderComponent', () => {
  let component: DialogWindowHeaderComponent;
  let fixture: ComponentFixture<DialogWindowHeaderComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DialogWindowHeaderComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DialogWindowHeaderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
