import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginPageComponent } from './components/login-page/login-page.component';
import {BrowserAnimationsModule, NoopAnimationsModule} from '@angular/platform-browser/animations';
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatInputModule} from "@angular/material/input";
import {MatButtonModule} from "@angular/material/button";
import { NotificationDialogComponent } from './components/dialog-boxes/notification-dialog/notification-dialog.component';
import {MatDialogModule} from "@angular/material/dialog";
import {MatIconModule} from "@angular/material/icon";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import { UserPageComponent } from './components/user-page/user-page.component';
import {AuthentificationInterceptor} from "./services/authentification.interceptor";
import {MatListModule} from "@angular/material/list";
import { NewFolderDialogComponent } from './components/dialog-boxes/new-folder-dialog/new-folder-dialog.component';
import {UploadFileDialogComponent} from "./components/dialog-boxes/upload-file-dialog/upload-file-dialog.component";
import {MatMenuModule} from "@angular/material/menu";
import {ConfirmationDialogComponent} from "./components/dialog-boxes/confirmation-dialog/confirmation-dialog.component";


@NgModule({
  declarations: [
    AppComponent,
    LoginPageComponent,
    NotificationDialogComponent,
    UserPageComponent,
    NewFolderDialogComponent,
    UploadFileDialogComponent,
    ConfirmationDialogComponent
  ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        NoopAnimationsModule,
        BrowserAnimationsModule,
        MatFormFieldModule,
        MatButtonModule,
        MatInputModule,
        MatDialogModule,
        MatIconModule,
        ReactiveFormsModule,
        HttpClientModule,
        MatListModule,
        FormsModule,
        MatMenuModule
    ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: AuthentificationInterceptor,  multi: true},
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
