import { Component, OnInit, Inject } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material';
import { CourseModel } from 'projects/portal-core/src/app/_common/models/course.model';
import { TrainerModel } from 'projects/portal-core/src/app/_common/models/trainer.model';
import { LocationModel } from 'projects/portal-core/src/app/_common/models/location.model';
import { CohortCourseModel } from 'projects/portal-core/src/app/_common/models/cohort-course.model';

export interface EditCourseDialogData {
  availableCourses: CourseModel[];
  availableTrainers: TrainerModel[];
  availableLocations: LocationModel[];
  delete?: boolean;
  meta: CohortCourseModel;
}

@Component({
  selector: 'app-edit-course-dialog',
  templateUrl: './edit-course-dialog.component.html'
})
export class EditCourseDialogComponent {

  selectedTrainer: string;

  constructor(@Inject(MAT_DIALOG_DATA) public data: EditCourseDialogData) { }



  onSubmit() {

  }

}
