import { Component, OnInit } from '@angular/core';
import {UserService} from "../../services/user.service";
import {FolderService} from "../../services/folder.service";
import {NotificationDialogComponent} from "../dialog-boxes/notification-dialog/notification-dialog.component";

@Component({
  selector: 'app-user-page',
  templateUrl: './user-page.component.html',
  styleUrls: ['./user-page.component.scss']
})
export class UserPageComponent implements OnInit {

  constructor(private folderService:FolderService) { }

  ngOnInit(): void {
    this.getRootFolder();
  }

  public getRootFolder(){
    this.folderService.getRootFolderForSignedInUser().subscribe(
      {
        next: response => {
          console.log(response)
          }
      }
    );
  }


}
