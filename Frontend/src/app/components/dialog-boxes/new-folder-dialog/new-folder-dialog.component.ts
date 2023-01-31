import { Component, OnInit,Inject } from '@angular/core';
import {MatDialog, MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';

export interface FolderNameData {
  folderName: string;
}

@Component({
  selector: 'app-new-folder-dialog',
  templateUrl: './new-folder-dialog.component.html',
  styleUrls: ['./new-folder-dialog.component.scss']
})
export class NewFolderDialogComponent implements OnInit {

  constructor(
    public dialogRef: MatDialogRef<NewFolderDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: FolderNameData
    ){}

  ngOnInit(): void {
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

}
