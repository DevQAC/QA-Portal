import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { UserModel } from 'projects/portal-core/src/app/_common/models/user.model';
import {UserDetailsModel} from '../../../../../portal-core/src/app/_common/models/user-details.model';

@Component({
  selector: 'app-delete-user-dialog',
  templateUrl: './delete-user-dialog.component.html'
})
export class DeleteUserDialogComponent implements OnInit {

  public canDelete = false;

  public numberOfUsers = 0;

  constructor(
    public dialogRef: MatDialogRef<DeleteUserDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: UserDetailsModel[]) { }

  ngOnInit() {
    // Brief pause before the delete button is enabled.
    // Hopefully people will use this time to think about what they're about to do.
    setTimeout(() => {
      this.canDelete = true;
    }, 1000);
    this.numberOfUsers = this.data.filter(d => d).length;
  }
}
