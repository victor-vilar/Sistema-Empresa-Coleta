import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LoginMainComponent } from './login-main.component';
import { Router } from '@angular/router';
import { DialogServiceService } from 'src/app/shared/services/dialog-service.service';

describe('LoginMainComponent', () => {
  let component: LoginMainComponent;
  let fixture: ComponentFixture<LoginMainComponent>;
  let router:jasmine.SpyObj<Router>;
  let dialogService:jasmine.SpyObj<DialogServiceService>

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LoginMainComponent ],
      providers:[
        {provide:Router,useValue:jasmine.createSpyObj('Router',['navigate'])},
        {provide:DialogServiceService,useValue:jasmine.createSpyObj('DialogService',['openProgressDialog', 'closeProgressSpinnerDialog'])}
      ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LoginMainComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
    router = TestBed.inject(Router) as jasmine.SpyObj<Router>;
    dialogService = TestBed.inject(DialogServiceService) as jasmine.SpyObj<DialogServiceService>;

  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
