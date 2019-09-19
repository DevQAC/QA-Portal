import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {MatDialogRef} from '@angular/material';
import {UserModel} from '../../../../portal-core/src/app/_common/models/user.model';

@Component({
  selector: 'app-add-user-dialog',
  templateUrl: './add-user-dialog.component.html',
  styleUrls: ['./add-user-dialog.component.css']
})
export class AddUserDialogComponent implements OnInit {

  @Output() public dataChanged = new EventEmitter();

  public data: UserModel = new UserModel();

  public canSubmit: boolean;


  constructor(public dialogRef: MatDialogRef<AddUserDialogComponent>) {
  }

  onSave(): void {
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
