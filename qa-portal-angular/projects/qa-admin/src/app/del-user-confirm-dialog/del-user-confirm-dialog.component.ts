import { Component, OnInit, Inject, Output, EventEmitter } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { IUserModel } from '../_common/models/user.model';

@Component({
  selector: 'app-del-user-confirm-dialog',
  templateUrl: './del-user-confirm-dialog.component.html',
  styleUrls: ['./del-user-confirm-dialog.component.css']
})
export class DelUserConfirmDialogComponent implements OnInit {
  @Output() public dataChanged = new EventEmitter();
  public canSubmit: boolean;

  constructor(public dialogRef: MatDialogRef<DelUserConfirmDialogComponent>, @Inject(MAT_DIALOG_DATA) public data: IUserModel) { }

  ngOnInit() {
  }

  onConfirmDel(): void {
    this.canSubmit = true;
    debugger;
    this.dataChanged.emit();
    this.dialogRef.close();
  }
  onCancel(): void {
    this.canSubmit = false;
    this.dataChanged.emit();
    this.dialogRef.close();
  }

}
