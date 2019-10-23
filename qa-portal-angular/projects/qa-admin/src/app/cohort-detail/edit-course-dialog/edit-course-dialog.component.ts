import { Component, OnInit, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material';
import { CourseModel } from 'projects/portal-core/src/app/_common/models/course.model';
import { TrainerModel } from 'projects/portal-core/src/app/_common/models/trainer.model';
import { LocationModel } from 'projects/portal-core/src/app/_common/models/location.model';
import { CohortCourseModel } from 'projects/portal-core/src/app/_common/models/cohort-course.model';
import * as moment from 'moment';

export interface EditCourseDialogData {
  availableCourses: CourseModel[];
  availableTrainers: TrainerModel[];
  availableLocations: LocationModel[];
  remove?: boolean;
  meta: CohortCourseModel;
}

@Component({
  selector: 'app-edit-course-dialog',
  templateUrl: './edit-course-dialog.component.html'
})
export class EditCourseDialogComponent implements OnInit {


  selectedTrainer: string;
  selectedLocation: number;
  selectedDuration: number;

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: EditCourseDialogData,
    private dialogRef: MatDialogRef<EditCourseDialogComponent>
  ) { }

  ngOnInit(): void {
    this.selectedTrainer = this.data.meta.trainer ? this.data.meta.trainer.userName : null;

    this.selectedLocation = this.data.meta.location ? this.data.meta.location.id : null;

    this.selectedDuration = moment(this.data.meta.endDate).diff(this.data.meta.startDate, 'days') + 1;
  }


  onSubmit() {
    this.data.meta.trainer = this.data.availableTrainers.find(t => t.user.userName === this.selectedTrainer).user;
    this.data.meta.location = this.data.availableLocations.find(l => l.id === this.selectedLocation);
    this.data.meta.endDate = moment(this.data.meta.startDate).add((this.selectedDuration || 1) - 1, 'days').toDate();
    this.dialogRef.close(this.data);
  }

}
