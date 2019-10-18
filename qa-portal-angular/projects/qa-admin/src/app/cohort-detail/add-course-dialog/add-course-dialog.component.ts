import { Component, OnInit, Inject } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material';
import { CourseModel } from 'projects/portal-core/src/app/_common/models/course.model';
import { TrainerModel } from 'projects/portal-core/src/app/_common/models/trainer.model';
import { LocationModel } from 'projects/portal-core/src/app/_common/models/location.model';

export interface AddCourseDialogData {
  availableCourses: CourseModel[];
  availableTrainers: TrainerModel[];
  availableLocations: LocationModel[];
  selectedCourse: string;
  selectedTrainer: string;
  selectedDuration: number;
  selectedLocation: LocationModel;
}

@Component({
  selector: 'app-add-course-dialog',
  templateUrl: './add-course-dialog.component.html'
})
export class AddCourseDialogComponent implements OnInit {

  constructor(@Inject(MAT_DIALOG_DATA) public data: AddCourseDialogData) { }

  ngOnInit() {
  }

}
