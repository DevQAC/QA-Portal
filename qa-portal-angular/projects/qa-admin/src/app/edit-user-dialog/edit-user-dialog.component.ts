import { Component, OnInit, Input, Inject, Output, EventEmitter, Optional } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { IUser } from '../_common/models/user.model';

@Component({
  selector: 'app-edit-user-dialog',
  templateUrl: './edit-user-dialog.component.html',
  styleUrls: ['./edit-user-dialog.component.css']
})
export class EditUserDialogComponent implements OnInit {
  @Output() public dataChanged = new EventEmitter<IUser>();
  private oldData: IUser;
  private passwordEditEnabled: boolean = false;

  constructor(public dialogRef: MatDialogRef<EditUserDialogComponent>, @Inject(MAT_DIALOG_DATA) public data: IUser) { }

  onSave(): void {
    this.dataChanged.emit(this.data);
    this.dialogRef.close();
  }
  onUpdate(): void {
    this.dataChanged.emit(this.data);
    this.dialogRef.close();
  }
  onCancel(): void {
    this.dataChanged.emit(this.oldData);
    this.dialogRef.close();
  }
  ngOnInit() {
    this.oldData = this.data;
  }

}
