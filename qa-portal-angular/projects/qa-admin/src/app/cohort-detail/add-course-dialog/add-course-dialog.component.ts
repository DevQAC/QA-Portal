import { Component, OnInit, Inject } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material';
import { CourseModel } from 'projects/portal-core/src/app/_common/models/course.model';
import { TrainerModel } from 'projects/portal-core/src/app/_common/models/trainer.model';

export interface AddCourseDialogData {
  availableCourses: CourseModel[];
  availableTrainers: TrainerModel[];
  selectedCourse: string;
  selectedTrainer: string;
  selectedDuration: number;
}

@Component({
  selector: 'app-add-course-dialog',
  templateUrl: './add-course-dialog.component.html',
  styleUrls: ['./add-course-dialog.component.css']
})
export class AddCourseDialogComponent implements OnInit {

  constructor(@Inject(MAT_DIALOG_DATA) public data: AddCourseDialogData) { }

  ngOnInit() {
  }

}
