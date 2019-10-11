import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { CohortService } from '../../_common/services/cohort.service';
import { QaErrorHandlerService } from 'projects/portal-core/src/app/_common/services/qa-error-handler.service';
import { finalize, take } from 'rxjs/operators';
import { MatDialogRef } from '@angular/material';
import { TrainerModel } from 'projects/portal-core/src/app/_common/models/trainer.model';

@Component({
  selector: 'app-new-cohort-dialog',
  templateUrl: './new-cohort-dialog.component.html',
  styleUrls: ['./new-cohort-dialog.component.css']
})
export class NewCohortDialogComponent implements OnInit {

  public cohortForm: FormGroup;
  public availableTrainers: TrainerModel[] = [];

  public isLoading = true;

  constructor(
    private cohortService: CohortService,
    private errorService: QaErrorHandlerService,
    public dialogRef: MatDialogRef<NewCohortDialogComponent>) {
    this.cohortForm = new FormBuilder().group({
      name: ['', [Validators.required]],
      startDate: [new Date(), [Validators.required]],
      trainerUserName: ['', [Validators.required]]
    });
  }

  ngOnInit(): void {
    this.cohortService.getAvailableTrainersForCohort().pipe(
      take(1),
      finalize(() => this.isLoading = false)
    ).subscribe(
      trainers => this.availableTrainers = trainers,
      err => this.errorService.handleError(err));
  }


  onSubmit(): void {
    const newCohort = {
      ...this.cohortForm.value
    };

    this.cohortService.addCohort(newCohort)
      .pipe(
        take(1),
        finalize(() => this.isLoading = false)
      ).subscribe(
        cohort => this.dialogRef.close(cohort),
        err => this.errorService.handleError(err)
      );
  }

}
