import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';

@Component({
  selector: 'app-del-user-confirm-dialog',
  templateUrl: './del-user-confirm-dialog.component.html',
  styleUrls: ['./del-user-confirm-dialog.component.css']
})
export class DelUserConfirmDialogComponent implements OnInit {

  constructor(public dialogRef: MatDialogRef<DelUserConfirmDialogComponent>, @Inject(MAT_DIALOG_DATA) public data: any) { }

  ngOnInit() {
  }

  onConfirmDel(): void {
    this.dialogRef.close();
  }
  onCancel(): void {
    this.dialogRef.close();
  }

}
