import { Component, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material';

@Component({
  selector: 'app-submit-confirm-dialog',
  templateUrl: './submit-confirm-dialog.component.html',
  styleUrls: ['./submit-confirm-dialog.component.css']
})
export class SubmitConfirmDialogComponent implements OnInit {

  constructor(public dialogRef: MatDialogRef<SubmitConfirmDialogComponent>) { }

  onNoClick(): void {
    this.dialogRef.close();
  }

  ngOnInit() {
  }

}
