import {FolderIdName} from "./folder-id-name";
import {File} from "./file";

export class Folder{
  id:number=-1;
  name:string="";
  files:File[]=[];
  childFolders:FolderIdName[]=[];
  parentFolder:FolderIdName = new FolderIdName();
}
