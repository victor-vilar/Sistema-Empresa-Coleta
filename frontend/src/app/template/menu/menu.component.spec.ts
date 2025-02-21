import { ComponentFixture, fakeAsync, TestBed, tick } from '@angular/core/testing';

import { MenuComponent } from './menu.component';
import { DialogServiceService } from 'src/app/shared/services/dialog-service.service';
import { LoginService } from 'src/app/login/services/login.service';
import { By } from '@angular/platform-browser';
import { NO_ERRORS_SCHEMA } from '@angular/core';
import { of } from 'rxjs';

describe('MenuComponent', () => {
  let component: MenuComponent;
  let fixture: ComponentFixture<MenuComponent>;
  let loginService:jasmine.SpyObj<LoginService>
  let dialogService:jasmine.SpyObj<DialogServiceService>
  let appUser = {username:'Madruguinha',password:'123456',roles:['Gerente'], profilePhotoUrl:'/m.jpg'};

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MenuComponent ],
      schemas:[NO_ERRORS_SCHEMA],
      providers:[
        {provide:LoginService,useValue:jasmine.createSpyObj('loginService',['logout'])},
        {provide:DialogServiceService,useValue:jasmine.createSpyObj('dialogService',['openConfirmCloseDialog'])}
      ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MenuComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();

    loginService = TestBed.inject(LoginService) as jasmine.SpyObj<LoginService>;
    dialogService = TestBed.inject(DialogServiceService) as jasmine.SpyObj<DialogServiceService>;
    
    dialogService.openConfirmCloseDialog.and.returnValue(of(true));

    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('get the logged user from loginService', () => {
    
    loginService.applicationUser = appUser;
    component.ngOnInit();
    expect(component.applicationUser).toEqual(loginService.applicationUser);
  })

  it('check if the view is getting the logged user data', () => {

    loginService.applicationUser = appUser;
    component.ngOnInit();
    
    fixture.detectChanges();

    const userData = fixture.debugElement.queryAll(By.css(".logged-user-data"));
    expect(userData[0].properties['src']).toBe(component.applicationUser.profilePhotoUrl);
    expect(userData[1].nativeElement.innerText).toBe(component.applicationUser.username);
    expect(userData[2].nativeElement.innerText).toContain(component.applicationUser.roles);

  });

  it('active logout method after click on logout button in the view', () => {
    
    loginService.applicationUser = appUser;
    component.ngOnInit();
    fixture.detectChanges();

    spyOn(component,'logout');
    const button = fixture.debugElement.query(By.css(".logout-button"));
    expect(button).toBeTruthy();
    
    button.children[0].nativeElement.click();
    expect(component.logout).toHaveBeenCalled();



  })

  it('should active the loginservice logout after click true on confirm dialog ', (fakeAsync(() =>{

    loginService.applicationUser = appUser;
    component.ngOnInit();
    fixture.detectChanges();

    const button = fixture.debugElement.query(By.css(".logout-button"));
    expect(button).toBeTruthy();
    
    button.children[0].nativeElement.click();


    tick(100);
    expect(loginService.logout).toHaveBeenCalled();

  }))) 


  
});
