import { DialogServiceService } from "src/app/shared/services/dialog-service.service";
import { LoginService } from "./login.service";
import { TestBed } from "@angular/core/testing";
import { HttpClientTestingModule, HttpTestingController } from "@angular/common/http/testing";
import { HttpClient, HttpResponse } from "@angular/common/http";
import { Router } from "@angular/router";
import { environment } from "src/environments/environment";
import { ApplicationUser } from "src/app/shared/entities/ApplicationUser";
import { of } from "rxjs";

fdescribe('LoginService',() => {


    let loginService:LoginService;
    let http:HttpClient;
    let httpTestingController:HttpTestingController;
    let router:jasmine.SpyObj<Router>;
    let dialogService:jasmine.SpyObj<DialogServiceService>
    let notLoggedUser:any;
    let loggedUser:any;
    let createHeadersSpy:any;
    let mockHttpResponse:any;
    let mockHttpHeader:any;

    beforeEach(async() =>{

        await TestBed.configureTestingModule({
            providers:[
                LoginService,
                {provide:Router,useValue:jasmine.createSpyObj('Router',['navigate'])},
                {provide:DialogServiceService,useValue:jasmine.createSpyObj('DialogService',['openProgressDialog', 'closeProgressSpinnerDialog'])}
            ],
            imports:[HttpClientTestingModule]
        })

        loginService = TestBed.inject(LoginService);
        http = TestBed.inject(HttpClient);
        router = TestBed.inject(Router) as jasmine.SpyObj<Router>
        dialogService = TestBed.inject(DialogServiceService) as jasmine.SpyObj<DialogServiceService>
        httpTestingController = TestBed.inject(HttpTestingController);

        loggedUser = {username:'Madruguinha',roles:['Gerente'], profilePhotoUrl:'/m.jpg'};
        notLoggedUser = {username:'Madruguinha', password:'123456'};
        createHeadersSpy = spyOn<any>(loginService,'createHeaders');


        mockHttpHeader = {
            Authorization:'this is a mock token',
            get: function(att:string){
                return this['att'];
            }
        }

        mockHttpResponse = {
            headers: mockHttpHeader,
            body:loggedUser
        }
    })


    it('Should test the login method successfully',(done:DoneFn) =>{

        let url = environment.API_URL + environment.API_VERSION + 'login';
        let getSpy = spyOn(http,'get').and.returnValue(of(mockHttpResponse));
        
        let ob$ = loginService.subscribeToLoginUser()
        .subscribe(response => {
            expect(response).toBeTrue
            done();
        })

        loginService.login(notLoggedUser);

        let objLogged = JSON.parse(window.sessionStorage.getItem('loggedUser'));
        expect(objLogged).toEqual(loggedUser);    
        expect(getSpy).toHaveBeenCalled();
        expect(dialogService.openProgressDialog).toHaveBeenCalled();
        expect(createHeadersSpy).toHaveBeenCalled();
        expect(router.navigate).toHaveBeenCalled();
    })


    it('should test the logout method successfully',(done:DoneFn) => {

        let getSpy = spyOn(http,'get').and.returnValue(of(mockHttpResponse));
        window.sessionStorage.setItem('loggedUser',JSON.stringify(loggedUser));

        let ob$ = loginService.subscribeToLoginUser()
        .subscribe(response => {
            expect(response).toBeFalse
            done();
        })

        loginService.logout();
        expect(getSpy).toHaveBeenCalled();
        expect(window.sessionStorage.getItem('loggedUser')).toBeNull();
        expect(loginService.applicationUser).toBeNull()
        expect(router.navigate).toHaveBeenCalledWith(['/login']);
    });


    it('should test getJwtToken method', () => {
        window.sessionStorage.setItem('jwtToken','myToken');
        const jwt = loginService.getJwtToken();
        expect(jwt).toBe('myToken');
    });

    it('should test getCsrfToken method',() => {
        window.sessionStorage.setItem('XSRF','xsrfToken');
        const xsrf = loginService.getCsrfToken();
        expect(xsrf).toBe('xsrfToken');
    });
})