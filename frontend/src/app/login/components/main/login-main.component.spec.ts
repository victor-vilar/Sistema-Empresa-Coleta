import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LoginMainComponent } from './login-main.component';
import { Router } from '@angular/router';
import { DialogServiceService } from 'src/app/shared/services/dialog-service.service';
import { ApplicationUser } from 'src/app/shared/entities/ApplicationUser';
import { LoginService } from '../../services/login.service';
import { FormsModule } from '@angular/forms';
import { SharedModule } from 'src/app/shared/shared.module';
import { By } from '@angular/platform-browser';

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
        {provide:LoginService,useValue:jasmine.createSpyObj('LoginService',['getUserFromBrownser','login'])},
        {provide:Router,useValue:jasmine.createSpyObj('Router',['navigate'])},
        {provide:DialogServiceService,useValue:jasmine.createSpyObj('DialogService',['openProgressDialog', 'openErrorDialog'])}
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
  });

  it('should active the login method after click in the view button',() =>{
    spyOn(component,'logar');
    const button = fixture.debugElement.query(By.css('button'))
    button.nativeElement.click();
    expect(component.logar).toHaveBeenCalled();
  });

  it('should create an applicationUser with the values of the view fields', () => {
    
    component.formulario.setValue({
      username:'Mock',
      password:'password'
    });

    fixture.detectChanges();
    let appUser = component.createsApplicationUser();
    expect(appUser.username).toBe('Mock');
    expect(appUser.password).toBe('password');

  });

  it('should throw an Error when the username field in the view it is empty',() =>{
    
    const msg = 'Os campos de usuario e senha não podem estar vazios !'
    component.formulario.setValue({
      username:'',
      password:'password'
    });

    fixture.detectChanges();
    
    //O codigo abaixo serve para eu conseguir testar se o dialogService.openError Dialog foi ativado
    try{
        component.createsApplicationUser();
    }catch(err){
        expect(dialogService.openErrorDialog).toHaveBeenCalledWith(msg);
    }

    //O código abaixo serve para testar o throws
    expect(() => component.createsApplicationUser()).toThrow(new Error(msg));
    
  });

  it('should throw an Error when the password field in the view it is empty',() =>{
    
    const msg = 'Os campos de usuario e senha não podem estar vazios !'
    component.formulario.setValue({
      username:'Mock',
      password:''
    });

    fixture.detectChanges();

    
    //O codigo abaixo serve para eu conseguir testar se o dialogService.openError Dialog foi ativado sem o throws atrapalhar
    try{
        component.createsApplicationUser();
    }catch(err){
        expect(dialogService.openErrorDialog).toHaveBeenCalledWith(msg);
    }
        
    //O código abaixo serve para testar o throws
    expect(() => component.createsApplicationUser()).toThrow(new Error(msg));
    
  });



});
