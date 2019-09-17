import { Component, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';

@Component({
  selector: 'app-delete-cohort-dialog',
  templateUrl: './delete-cohort-dialog.component.html'
})
export class DeleteCohortDialogComponent {

  constructor(
    public dialogRef: MatDialogRef<DeleteCohortDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: boolean) { }

}
