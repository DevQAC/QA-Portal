import {Component, Inject, OnInit} from '@angular/core';
import {MatDialogRef, MAT_DIALOG_DATA} from '@angular/material';
import {CohortService} from '../../_common/services/cohort.service';
import {QaErrorHandlerService} from '../../../../../portal-core/src/app/_common/services/qa-error-handler.service';

@Component({
  selector: 'app-update-user-cohort-dialog',
  templateUrl: './update-user-cohort-dialog.component.html'
})
export class UpdateUserCohortDialogComponent implements OnInit {
  public cohortSelection = '';

  public cohorts = [];

  constructor(
    public dialogRef: MatDialogRef<UpdateUserCohortDialogComponent>,
    private cohortService: CohortService,
    private errorService: QaErrorHandlerService,
    @Inject(MAT_DIALOG_DATA) public data: string) {
  }

  ngOnInit(): void {
    this.cohortService.searchCohorts('').subscribe((response) => {
        response.forEach((cohortModel) => {
          this.cohorts.push(cohortModel.name);
        });
      },
      (error) => {
        this.errorService.handleError(error);
      });
  }

}
