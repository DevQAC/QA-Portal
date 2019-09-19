import { Component, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';

@Component({
  selector: 'app-update-user-cohort-dialog',
  templateUrl: './update-user-cohort-dialog.component.html'
})
export class UpdateUserCohortDialogComponent {
  constructor(
    public dialogRef: MatDialogRef<UpdateUserCohortDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: boolean) { }

}
