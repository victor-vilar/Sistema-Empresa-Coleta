import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ErrorDialogComponent } from './error-dialog.component';
import { SharedModule } from '../../shared.module';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { By } from '@angular/platform-browser';

describe('ErrorDialogComponent', () => {
  let component: ErrorDialogComponent;
  let fixture: ComponentFixture<ErrorDialogComponent>;
  let data = {error:'this is a error test message'};

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ErrorDialogComponent ],
      imports:[SharedModule],
      providers:[
        {provide: MatDialogRef,useValue: {}},
        {provide: MAT_DIALOG_DATA,useValue: data}
      ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ErrorDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should render error message in the view on init' ,() => {
    let div = fixture.debugElement.query(By.css('[data-test-div="error-message-div"]')).nativeElement;
    component.ngOnInit();
    fixture.detectChanges();
    expect(component.errorMessage).toBe(data.error);
    expect(div.innerText).toBe(component.errorMessage);
  })
});
