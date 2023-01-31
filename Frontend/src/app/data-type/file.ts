import {FolderIdName} from "./folder-id-name";

export class File {
  id:number=-1;
  name:string="";
  parentFolder:FolderIdName= new FolderIdName();
}
