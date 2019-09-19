import { Component, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';

@Component({
  selector: 'app-delete-user-dialog',
  templateUrl: './delete-user-dialog.component.html'
})
export class DeleteUserDialogComponent {
  constructor(
    public dialogRef: MatDialogRef<DeleteUserDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: boolean) { }
}
