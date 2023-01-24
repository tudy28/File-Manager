import { Injectable } from '@angular/core';
import {UserCredentials} from "../data-type/user-credentials";
import {Observable} from "rxjs";
import {HttpClient, HttpHeaders} from "@angular/common/http";

const bodyUrl="http://localhost:8080";
const LOGIN = bodyUrl+"/auth/login";

@Injectable({
  providedIn: 'root'
})

export class UserService {

  constructor(private httpClient:HttpClient) { }


  public loginUser(userCredentials: UserCredentials): Observable<any> {
    return this.httpClient.post<any>(LOGIN, userCredentials);
  }

}
