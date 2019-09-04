import { Component, OnInit, Input, Inject, Output, EventEmitter, Optional } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { IUser } from '../_common/models/user.model';

@Component({
  selector: 'app-add-user-dialog',
  templateUrl: './add-user-dialog.component.html',
  styleUrls: ['./add-user-dialog.component.css']
})
export class AddUserDialogComponent implements OnInit {
  @Output() public dataChanged = new EventEmitter<IUser>();
  private data: IUser = {
    username: "",
    firstName: "",
    lastName: "",
    email: "",
    roles: []
  };

  constructor(public dialogRef: MatDialogRef<AddUserDialogComponent>) { }

  onSave(): void {
    this.dataChanged.emit(this.data);
    this.dialogRef.close();
  }
  onUpdate(): void {
    this.dataChanged.emit(this.data);
    this.dialogRef.close();
  }
  onCancel(): void {
    this.dataChanged.emit(this.data);
    this.dialogRef.close();
  }
  ngOnInit() {
    this.data = this.data;
  }

}
