import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SuccessDialogComponent } from './success-dialog.component';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { SharedModule } from '../../shared.module';
import { By } from '@angular/platform-browser';

describe('SuccessDialogComponent', () => {
  let component: SuccessDialogComponent;
  let fixture: ComponentFixture<SuccessDialogComponent>;
  let data = {message:'this is a successfull message'}

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SuccessDialogComponent ],
      imports:[SharedModule],
      providers:[
        {provide: MatDialogRef,useValue: {}},
        {provide: MAT_DIALOG_DATA,useValue: data}
      ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SuccessDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

    it('should render the message in the view on init' ,() => {
      let div = fixture.debugElement.query(By.css('[data-test-div="error-message-div"]')).nativeElement;
      component.ngOnInit();
      fixture.detectChanges();
      expect(component.message).toBe(data.message);
      expect(div.innerText).toBe(component.message);
    })
});
