import {Component, OnInit} from '@angular/core';
import {UserService} from "../../services/user.service";
import {FolderService} from "../../services/folder.service";
import {NotificationDialogComponent} from "../dialog-boxes/notification-dialog/notification-dialog.component";
import {Folder} from "../../data-type/folder";
import {MatDialog} from "@angular/material/dialog";
import {NewFolderDialogComponent} from "../dialog-boxes/new-folder-dialog/new-folder-dialog.component";
import {FolderCreateDto} from "../../data-type/folder-create-dto";
import {UploadFileDialogComponent} from "../dialog-boxes/upload-file-dialog/upload-file-dialog.component";
import {FileCreateDto} from "../../data-type/file-create-dto";
import {ConfirmationDialogComponent} from "../dialog-boxes/confirmation-dialog/confirmation-dialog.component";
import {File} from "../../data-type/file";
import {FileService} from "../../services/file.service";
import {InputDialogComponent} from "../dialog-boxes/input-dialog/input-dialog.component";
import {FolderIdName} from "../../data-type/folder-id-name";
import {FileContentDto} from "../../data-type/file-content-dto";

@Component({
  selector: 'app-user-page',
  templateUrl: './user-page.component.html',
  styleUrls: ['./user-page.component.scss']
})
export class UserPageComponent implements OnInit {

  currentFolder: Folder = new Folder();

  constructor(private folderService: FolderService,
              private userService: UserService,
              private dialog: MatDialog,
              private fileService: FileService) {
  }

  ngOnInit(): void {
    this.getRootFolder();
  }

  public getRootFolder() {
    this.userService.getSignedInUser().subscribe(
      {
        next: userInfo => {
          this.folderService.getFolderById(userInfo.rootFolderId).subscribe(
            {
              next: folder => {
                this.currentFolder = folder;
              }
            });
        }
      });
  }


  public accessChildFolder(chidlFolderId: number) {
    this.folderService.getFolderById(chidlFolderId).subscribe(
      {
        next: folder => {
          this.currentFolder = folder;
        }
      });
  }

  public goToParentDirectory() {
    this.folderService.getFolderById(this.currentFolder.parentFolder.id).subscribe(
      {
        next: folder => {
          this.currentFolder = folder;
        }
      });
  }

  public createNewFolder() {
    const dialogRef = this.dialog.open(NewFolderDialogComponent, {
      data: {folderName: ''},
    });

    dialogRef.afterClosed().subscribe(folderName => {
      if (folderName != undefined && folderName != "") {
        const newFolderObject: FolderCreateDto = {
          folderName: folderName,
          folderParentId: this.currentFolder.id
        }

        this.folderService.createNewFolder(newFolderObject).subscribe({
          next: createdFolder => {
            this.currentFolder.childFolders.push(createdFolder);
          }
        });

      }
    });
  }

  public uploadFile() {
    const dialogRef = this.dialog.open(UploadFileDialogComponent, {
      data: {fileName: '', bytes: []},
    });
    dialogRef.afterClosed().subscribe(data => {
      if (data.fileName != undefined && data.fileName != "" && data.bytes != null) {
        const newFileObject: FileCreateDto = {
          name: data.fileName,
          content: data.bytes,
          parentFolderId: this.currentFolder.id
        }
        this.folderService.uploadNewFile(newFileObject).subscribe({
          next: createdFile => {
            this.currentFolder.files.push(createdFile);
          }
        });

      }
    });
  }

  deleteFile(file: File) {
    const dialogRef = this.dialog.open(ConfirmationDialogComponent, {
      data: "Are you sure you want to delete " + file.name + " ?",
      autoFocus: false
    });
    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.fileService.deleteFileById(file.id).subscribe(
          _ => {
            const index = this.currentFolder.files.findIndex(fileIt => file.id == fileIt.id)
            this.currentFolder.files.splice(index, 1);
          }
        )

      }
    });
  }

  renameFile(file: File) {
    const dialogRef = this.dialog.open(InputDialogComponent, {
      data: {newName: file.name},
      autoFocus: false
    });
    dialogRef.afterClosed().subscribe(result => {
      if (result.newName != undefined && result.newName != "") {
        const newFileObject: any = {
          id: file.id,
          name: result.newName,
        }
        this.fileService.renameFile(newFileObject).subscribe(
          renamedFile => {
            const index = this.currentFolder.files.findIndex(fileIt => file.id == fileIt.id)
            this.currentFolder.files[index] = renamedFile
          }
        )

      }
    });

  }

  downloadFile(file: File) {
    this.fileService.getFileContents(file.id).subscribe(
      fileContentDto =>{
        const element = document.createElement("a");
        var base64 = btoa(
          new Uint8Array(fileContentDto.content)
            .reduce((data, byte) => data + String.fromCharCode(byte), '')
        );
        element.setAttribute("href", "data:application/octet-stream;base64," + base64);
        element.setAttribute("download", file.name);
        element.style.display = "none";
        document.body.appendChild(element);
        element.click();
        document.body.removeChild(element);
      }
    )

  }

  renameFolder(folder: FolderIdName) {
    const dialogRef = this.dialog.open(InputDialogComponent, {
      data: {newName: folder.name},
      autoFocus: false
    });
    dialogRef.afterClosed().subscribe(result => {
      if (result.newName != undefined && result.newName != "") {
        const newFolderObject: any = {
          id: folder.id,
          name: result.newName,
        }
        this.folderService.renameFolder(newFolderObject).subscribe(
          renamedFolder => {
            const index = this.currentFolder.childFolders.findIndex(folderIt => folder.id == folderIt.id)
            this.currentFolder.childFolders[index] = renamedFolder
          }
        )
      }
    })
  }

  deleteFolder(folder:FolderIdName) {
    const dialogRef = this.dialog.open(ConfirmationDialogComponent, {
      data: "Are you sure you want to delete " + folder.name + " ?",
      autoFocus: false
    });
    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.folderService.deleteFolderById(folder.id).subscribe(
          _ => {
            const index = this.currentFolder.childFolders.findIndex(folderIt => folder.id == folderIt.id)
            console.log(index)
            this.currentFolder.childFolders.splice(index, 1);
          }
        )

      }
    });
  }
}
