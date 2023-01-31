import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {UserCredentials} from "../data-type/user-credentials";
import {Observable} from "rxjs";
import {Folder} from "../data-type/folder";
import {FolderCreateDto} from "../data-type/folder-create-dto";
import {FolderIdName} from "../data-type/folder-id-name";
import {FileCreateDto} from "../data-type/file-create-dto";
import {File} from "../data-type/file";
import {FileRenameDto} from "../data-type/file-rename-dto";

const bodyUrl="http://localhost:8080";
const GET_FOLDER = bodyUrl+"/folders/";
const CREATE_NEW_FOLDER=bodyUrl+"/folders/new-folder";
const UPLOAD_NEW_FILE=bodyUrl+"/files/upload-file";

@Injectable({
  providedIn: 'root'
})
export class FolderService {

  constructor(private httpClient:HttpClient) {

  }

  public getFolderById(folderId: number): Observable<Folder> {
    return this.httpClient.get<Folder>(GET_FOLDER+folderId);
  }

  public createNewFolder(newFolderObject:FolderCreateDto): Observable<FolderIdName>{
    return this.httpClient.post<FolderIdName>(CREATE_NEW_FOLDER,newFolderObject);
  }

  public uploadNewFile(newFileObject: FileCreateDto):Observable<File> {
    return this.httpClient.post<File>(UPLOAD_NEW_FILE,newFileObject);
  }

}
