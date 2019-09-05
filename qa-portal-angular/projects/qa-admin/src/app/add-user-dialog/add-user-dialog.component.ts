import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { MatDialogRef } from '@angular/material';
import { IUserModel } from '../_common/models/user.model';

@Component({
  selector: 'app-add-user-dialog',
  templateUrl: './add-user-dialog.component.html',
  styleUrls: ['./add-user-dialog.component.css']
})
export class AddUserDialogComponent implements OnInit {
  @Output() public dataChanged = new EventEmitter();
  public data: IUserModel = {
    id: null,
    username: "",
    firstName: "",
    lastName: "",
    email: "",
    roles: [],
    cohorts: []
  };
  public canSubmit: boolean;


  constructor(public dialogRef: MatDialogRef<AddUserDialogComponent>) { }

  onSave(): void {
    debugger;
    this.canSubmit = true;
    this.dataChanged.emit();
    this.dialogRef.close();
  }
  onCancel(): void {
    this.canSubmit = false;
    this.dataChanged.emit();
    this.dialogRef.close();
  }
  ngOnInit() {
  }

}
