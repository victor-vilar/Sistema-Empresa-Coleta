import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LoginMainComponent } from './login-main.component';
import { Router } from '@angular/router';
import { DialogServiceService } from 'src/app/shared/services/dialog-service.service';
import { ApplicationUser } from 'src/app/shared/entities/ApplicationUser';
import { LoginService } from '../../services/login.service';
import { FormsModule } from '@angular/forms';
import { SharedModule } from 'src/app/shared/shared.module';

fdescribe('LoginMainComponent', () => {
  
  let component: LoginMainComponent;
  let loginService:jasmine.SpyObj<LoginService>
  let fixture: ComponentFixture<LoginMainComponent>;
  let router:jasmine.SpyObj<Router>;
  let dialogService:jasmine.SpyObj<DialogServiceService>;
  let loggedUser:ApplicationUser;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LoginMainComponent ],
      providers:[
        {provide:LoginService,useValue:jasmine.createSpyObj('LoginService',['getUserFromBrownser'])},
        {provide:Router,useValue:jasmine.createSpyObj('Router',['navigate'])},
        {provide:DialogServiceService,useValue:jasmine.createSpyObj('DialogService',['openProgressDialog', 'closeProgressSpinnerDialog'])}
      ],
      imports:[FormsModule,SharedModule]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LoginMainComponent);
    router = TestBed.inject(Router) as jasmine.SpyObj<Router>;
    dialogService = TestBed.inject(DialogServiceService) as jasmine.SpyObj<DialogServiceService>;
    loginService = TestBed.inject(LoginService) as jasmine.SpyObj<LoginService>;
    loggedUser = {username:'Madruguinha',roles:['Gerente'], profilePhotoUrl:'/m.jpg'};
    
    
    component = fixture.componentInstance;
    fixture.detectChanges();
    window.sessionStorage.clear();
    
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should navigate to dashboard component if already exist a logged user',() => {
    loginService.getUserFromBrownser.and.returnValue(loggedUser);
    component.ngOnInit();
    expect(router.navigate).toHaveBeenCalledWith(['/dashboard'])
  });

  it('should not navigate to dashboard component if there is no logged user return null ',() => {
    loginService.getUserFromBrownser.and.returnValue(null);
    component.ngOnInit();
    expect(router.navigate).not.toHaveBeenCalled();
  });

  it('should not navigate to dashboard component if there is no logged user return null ',() => {
    loginService.getUserFromBrownser.and.returnValue(undefined);
    component.ngOnInit();
    expect(router.navigate).not.toHaveBeenCalled();
  })
});
