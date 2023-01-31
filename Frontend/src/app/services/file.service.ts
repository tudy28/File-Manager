import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Folder} from "../data-type/folder";

const bodyUrl="http://localhost:8080";
const DELETE_FILE = bodyUrl + "/files/";

@Injectable({
  providedIn: 'root'
})
export class FileService {

  constructor(private httpClient: HttpClient) {}

  public deleteFileById(fileId: number): Observable<any> {
    return this.httpClient.delete<any>(DELETE_FILE+fileId);
  }

}
