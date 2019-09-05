import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { MatDialogRef } from '@angular/material';

@Component({
  selector: 'app-submit-confirm-dialog',
  templateUrl: './submit-confirm-dialog.component.html',
  styleUrls: ['./submit-confirm-dialog.component.css']
})
export class SubmitConfirmDialogComponent implements OnInit {
  @Output() public doSubmit = new EventEmitter();

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

  ngOnInit() {
  }

}
