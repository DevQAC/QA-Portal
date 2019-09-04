import { Component, OnInit, Input, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { IUser } from '../_common/models/user.model';

@Component({
  selector: 'app-add-user-dialog',
  templateUrl: './add-user-dialog.component.html',
  styleUrls: ['./add-user-dialog.component.css']
})
export class AddUserDialogComponent implements OnInit {
  constructor(public dialogRef: MatDialogRef<AddUserDialogComponent>, @Inject(MAT_DIALOG_DATA) public data: IUser) { }

  onSave(): void {
    this.dialogRef.close();
  }
  onUpdate(): void {
    this.dialogRef.close();
  }
  onCancel(): void {
    this.dialogRef.close();
  }
  ngOnInit() {
  }

}
