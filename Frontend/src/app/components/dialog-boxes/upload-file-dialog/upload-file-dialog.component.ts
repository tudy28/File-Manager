import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
export interface FileData {
  fileName: string;
  bytes:any
}

@Component({
  selector: 'app-upload-file-dialog',
  templateUrl: './upload-file-dialog.component.html',
  styleUrls: ['./upload-file-dialog.component.scss']
})
export class UploadFileDialogComponent implements OnInit {

  constructor(
    public dialogRef: MatDialogRef<UploadFileDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: FileData
  ){}

  ngOnInit(): void {
  }

  onNoClick(): void {
    this.dialogRef.close();
  }



  public onFileSelected(event: Event) {
    // @ts-ignore
    const file=event.target.files[0]
    this.data.fileName=file.name
    var reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onload = () => {
      this.data.bytes = reader.result as string;
      this.data.bytes=this.data.bytes.split(",")[1];
    };

  }

}

