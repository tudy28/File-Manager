import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormBuilder, Validators} from "@angular/forms";
import {MatDialog} from "@angular/material/dialog";
import {UserCredentials} from "../../data-type/user-credentials";
import {UserService} from "../../services/user.service";
import {Router} from "@angular/router";
import {NotificationDialogComponent} from "../dialog-boxes/notification-dialog/notification-dialog.component";

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.scss']
})
export class LoginPageComponent implements OnInit {

  @Output() clickCreate = new EventEmitter<JSON>();
  userCredentialsFormGroup = this.formBuilder.group({
    username: ["",[Validators.required]] ,
    password: ["",[Validators.required]],
  })

  public hide:boolean = true;


  constructor(private formBuilder: FormBuilder,
              private dialog:MatDialog,
              private userService:UserService,
              private router: Router
  ) { }

  ngOnInit(): void {
  }

  getUsernameErrorMessage() {
    return this.userCredentialsFormGroup.controls["username"].hasError('required') ? 'Username cannot be empty!' : '';
  }


  getPasswordErrorMessage() {
    return this.userCredentialsFormGroup.controls["password"].hasError('required') ? 'Password cannot be empty!' : '';
  }

  public loginUser() {
    const valuesFromForm = this.userCredentialsFormGroup.value;
    const userCredentials: UserCredentials = {
      username: valuesFromForm.username!,
      password: valuesFromForm.password!,
    };
    // @ts-ignore
    if (this.getPasswordErrorMessage() == "" && this.getUsernameErrorMessage() == "") {
      this.userService.loginUser(userCredentials).subscribe({
        next: response => {
          console.log(response)
          document.cookie = "Token = " + response['token'] + "; path=/";
          this.router.navigate(['../home']);
        },
        error: err => {
          this.dialog.open(NotificationDialogComponent, {
            width: '250px', data: "There is no account matching your credentials!",
            autoFocus: false
          });
        }
      });
    }
  }



}
