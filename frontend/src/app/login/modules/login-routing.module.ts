import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { LoginMainComponent } from '../components/main/login-main.component';



const routes: Routes = [
  {path:'login',component:LoginMainComponent},
]



@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    RouterModule.forChild(routes)
  ],
  exports:[RouterModule]
})
export class LoginRoutingModule { }
