import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {UserCredentials} from "../data-type/user-credentials";
import {Observable} from "rxjs";

const bodyUrl="http://localhost:8080";
const GET_ROOT_FOLDER = bodyUrl+"/folders/user-root-folder";


@Injectable({
  providedIn: 'root'
})
export class FolderService {

  constructor(private httpClient:HttpClient) {

  }

  public getRootFolderForSignedInUser(): Observable<any> {
    return this.httpClient.get<any>(GET_ROOT_FOLDER);
  }
}
