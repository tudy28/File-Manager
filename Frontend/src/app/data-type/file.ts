import {FolderIdName} from "./folder-id-name";

export class File {
  id:number=-1;
  name:string="";
  content:any;
  parentFolder:FolderIdName= new FolderIdName();
}
