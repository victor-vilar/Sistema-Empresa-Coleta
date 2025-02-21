import { DialogServiceService } from "src/app/shared/services/dialog-service.service";
import { LoginService } from "./login.service";
import { TestBed } from "@angular/core/testing";
import { HttpClientTestingModule, HttpTestingController } from "@angular/common/http/testing";
import { HttpClient } from "@angular/common/http";
import { Router } from "@angular/router";

describe('LoginService',() => {


    let loginService:LoginService;
    let httpClient:HttpClient;
    let httpTestingController:HttpTestingController;
    let router:jasmine.SpyObj<Router>;
    let dialogService:jasmine.SpyObj<DialogServiceService>
    let appUser:any;

    beforeEach(async() =>{

        await TestBed.configureTestingModule({
            providers:[
                LoginService,
                HttpClientTestingModule,
                {provide:Router,useValue:jasmine.createSpyObj('Router',['navigate'])},
                {provide:DialogServiceService,useValue:jasmine.createSpyObj('DialogService',['openProgressDialog'])}
            ]
        })

        loginService = TestBed.inject(LoginService);
        httpClient = TestBed.inject(HttpClient);
        router = TestBed.inject(Router) as jasmine.SpyObj<Router>
        dialogService = TestBed.inject(DialogServiceService) as jasmine.SpyObj<DialogServiceService>
        httpTestingController = TestBed.inject(HttpTestingController);
        appUser = {username:'Madruguinha',password:'123456',roles:['Gerente'], profilePhotoUrl:'/m.jpg'};
        window.sessionStorage.setItem('loggedUser',JSON.stringify(appUser))
    })


    it('Should test the login method successfully',() =>{



    })

})