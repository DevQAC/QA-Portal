import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { MatDialogRef } from '@angular/material';

@Component({
  selector: 'app-submit-confirm-dialog',
  templateUrl: './submit-confirm-dialog.component.html',
  styleUrls: ['./submit-confirm-dialog.component.css']
})
export class SubmitConfirmDialogComponent implements OnInit {
  @Output() public doSubmit = new EventEmitter<boolean>();

  private canSubmit: boolean;

  constructor(public dialogRef: MatDialogRef<SubmitConfirmDialogComponent>) { }

  onNoClick(): void {
    this.dialogRef.close();
  }

  onSubmit(): void {
    this.canSubmit = true;
    this.doSubmit.emit(this.canSubmit);
    debugger;
    this.dialogRef.close();
  }

  ngOnInit() {
  }

}
