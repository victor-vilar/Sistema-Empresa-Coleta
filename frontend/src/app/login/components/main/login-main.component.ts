import { DialogServiceService } from '../../../shared/services/dialog-service.service';
import { Component, inject, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { LoginService } from '../../services/login.service';
import { ApplicationUser } from '../../../shared/entities/ApplicationUser';
import { getCookie } from 'typescript-cookie';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login-main',
  templateUrl: './login-main.component.html',
  styleUrls: ['./login-main.component.css']
})
export class LoginMainComponent implements OnInit {

  @ViewChild('meuForm') formulario:NgForm;

  private loginService:LoginService = inject(LoginService);
  private router:Router = inject(Router);
  private dialogService:DialogServiceService = inject(DialogServiceService);
  
  constructor() { }


  ngOnInit(): void {
    if(this.loginService.getUserFromBrownser() !== null && this.loginService.getUserFromBrownser() !== undefined){
      this.router.navigate(["/dashboard"]);
    }
  }

  logar(){
    this.loginService.login(this.createsApplicationUser());
  }

  createsApplicationUser():ApplicationUser{


    if(this.formulario.value.username === "" || this.formulario.value.password === ""){
      this.dialogService.openErrorDialog('Os campos de usuario e senha não podem estar vazios !');
      throw Error("username and password fields can't be null")
    }

    return {
      username: this.formulario.value.username,
      password: this.formulario.value.password
    }

  }






}
