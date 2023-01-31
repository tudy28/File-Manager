import { Injectable } from '@angular/core';
import {UserCredentials} from "../data-type/user-credentials";
import {Observable} from "rxjs";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {User} from "../data-type/user";

const bodyUrl="http://localhost:8080";
const LOGIN = bodyUrl+"/auth/login";
const GET_SIGNED_IN_USER = bodyUrl + "/users/signed-in-user";
@Injectable({
  providedIn: 'root'
})

export class UserService {

  constructor(private httpClient:HttpClient) { }


  public loginUser(userCredentials: UserCredentials): Observable<any> {
    return this.httpClient.post<any>(LOGIN, userCredentials);
  }

  public getSignedInUser(): Observable<User>{
    return this.httpClient.get<User>(GET_SIGNED_IN_USER);
  }

}
