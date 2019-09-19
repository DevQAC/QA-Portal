import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';

@Component({
  selector: 'app-update-user-cohort-dialog',
  templateUrl: './update-user-cohort-dialog.component.html'
})
export class UpdateUserCohortDialogComponent implements OnInit {
  public cohortSelection = '';

  public cohorts = [];

  constructor(
    public dialogRef: MatDialogRef<UpdateUserCohortDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: string) { }

    ngOnInit(): void {
      this.cohorts = [
        'This',
        'Component',
        'Is',
        'Not',
        'Yet',
        'Implemented'
      ];
    }

}
