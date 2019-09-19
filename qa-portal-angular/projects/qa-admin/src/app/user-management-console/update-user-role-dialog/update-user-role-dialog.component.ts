import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { UpdateUserCohortDialogComponent } from '../update-user-cohort-dialog/update-user-cohort-dialog.component';

@Component({
  selector: 'app-update-user-role-dialog',
  templateUrl: './update-user-role-dialog.component.html'
})
export class UpdateUserRoleDialogComponent implements OnInit {

  public roleInput = '';

  public roles = [];

  constructor(
    public dialogRef: MatDialogRef<UpdateUserCohortDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: boolean) { }

  ngOnInit(): void {
    this.roles = [
      'This',
      'Component',
      'Is',
      'Not',
      'Yet',
      'Implemented'
    ];
  }
}
