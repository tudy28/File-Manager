import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Folder} from "../data-type/folder";
import {FileContentDto} from "../data-type/file-content-dto";

const bodyUrl="http://localhost:8080";
const DELETE_FILE = bodyUrl + "/files/";
const RENAME_FILE = bodyUrl + "/files/rename";


@Injectable({
  providedIn: 'root'
})
export class FileService {

  constructor(private httpClient: HttpClient) {}

  public deleteFileById(fileId: number): Observable<any> {
    return this.httpClient.delete<any>(DELETE_FILE+fileId);
  }

  renameFile(fileBody: any):Observable<any> {
    return this.httpClient.put<any>(RENAME_FILE,fileBody);
  }

  getFileContents(fileId:number):Observable<FileContentDto>{
    return this.httpClient.get<FileContentDto>(bodyUrl + "/files/"+fileId+"/content");
  }
}
