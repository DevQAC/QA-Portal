import { Component, OnInit, Inject, Output, EventEmitter } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { IUserModel } from '../_common/models/user.model';

@Component({
  selector: 'app-edit-user-dialog',
  templateUrl: './edit-user-dialog.component.html',
  styleUrls: ['./edit-user-dialog.component.css']
})
export class EditUserDialogComponent implements OnInit {
  @Output() public dataChanged = new EventEmitter();
  private oldData: IUserModel;
  public canSubmit: boolean;
  public passwordEditEnabled: boolean;

  constructor(public dialogRef: MatDialogRef<EditUserDialogComponent>, @Inject(MAT_DIALOG_DATA) public data: IUserModel) { }


  onUpdate(): void {
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
    this.oldData = this.data;
  }

}
