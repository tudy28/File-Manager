import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
export interface FileData {
  newName: string;
}
@Component({
  selector: 'app-input-dialog',
  templateUrl: './input-dialog.component.html',
  styleUrls: ['./input-dialog.component.scss']
})
export class InputDialogComponent implements OnInit {

  constructor(
    public dialogRef: MatDialogRef<InputDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: FileData
  ){}

  ngOnInit(): void {
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

}
