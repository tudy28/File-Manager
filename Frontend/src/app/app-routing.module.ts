import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {LoginPageComponent} from "./components/login-page/login-page.component";
import {UserPageComponent} from "./components/user-page/user-page.component";


const routes: Routes = [
  {path:"login",component:LoginPageComponent},
  {path:"home",component:UserPageComponent},




  {path: "**",redirectTo:'login'},
]


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule { }

