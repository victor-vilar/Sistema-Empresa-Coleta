import { ComponentFixture, TestBed } from '@angular/core/testing';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ConfirmationDialogComponent } from './confirmation-dialog.component';
import { By } from '@angular/platform-browser';
import { SharedModule } from '../../shared.module';


describe('ConfirmationDialogComponent', () => {
  let component: ConfirmationDialogComponent;
  let fixture: ComponentFixture<ConfirmationDialogComponent>;
  let data = {text:'this is a test'}
  let div:HTMLDivElement;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ConfirmationDialogComponent ],
      imports:[SharedModule],
      providers:[
        {provide: MatDialogRef,useValue: jasmine.createSpy('close')},
        {provide: MAT_DIALOG_DATA,useValue: data}
      ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ConfirmationDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
    div = fixture.debugElement.queryAll(By.css('div'))[0].nativeElement;
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should set view message as message variable if data it is not null or undefined',() =>{
    component.ngOnInit();
    fixture.detectChanges();
    expect(component.message).toEqual(component.data.text)
    expect(div.innerText).toBe(component.message);
  });

  it('should set a default message if data variable it is null or undefined',() => {
    component.data = undefined;
    component.ngOnInit();
    fixture.detectChanges();
    expect(component.message).toEqual('Gostaria de deletar ?');
    expect(div.innerText).toBe(component.message);
  });

});
