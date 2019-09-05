import { Component, Output, EventEmitter } from '@angular/core';
import { MatDialogRef } from '@angular/material';

@Component({
  selector: 'app-submit-confirm-dialog',
  templateUrl: './submit-confirm-dialog.component.html'
})
export class SubmitConfirmDialogComponent {
  @Output() public doSubmit = new EventEmitter<boolean>();

  public canSubmit: boolean;

  constructor(public dialogRef: MatDialogRef<SubmitConfirmDialogComponent>) { }

  onNoClick(): void {
    this.canSubmit = false;
    this.doSubmit.emit();
    this.dialogRef.close();
  }

  onSubmit(): void {
    this.canSubmit = true;
    this.doSubmit.emit();
    this.dialogRef.close();
  }
}
