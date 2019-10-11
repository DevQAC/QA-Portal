import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { CourseService } from '../../_common/services/course.service';
import { MatDialogRef } from '@angular/material';
import { QaErrorHandlerService } from 'projects/portal-core/src/app/_common/services/qa-error-handler.service';
import { finalize, take } from 'rxjs/operators';
import { TechnologyService } from '../../_common/technology.service';
import { TechnologyCategoryModel } from 'projects/portal-core/src/app/_common/models/technology-category.model';

@Component({
  selector: 'app-new-course-dialog',
  templateUrl: './new-course-dialog.component.html',
  styleUrls: ['./new-course-dialog.component.css']
})
export class NewCourseDialogComponent implements OnInit {

  public courseForm: FormGroup;
  public isLoading = true;
  public availableTechCategories: TechnologyCategoryModel[];

  constructor(
    private courseService: CourseService,
    private techService: TechnologyService,
    public dialogRef: MatDialogRef<NewCourseDialogComponent>,
    private errorService: QaErrorHandlerService
  ) {
    this.courseForm = new FormBuilder().group({
      courseName: ['', Validators.required],
      duration: [5, [Validators.required, Validators.min(1), Validators.max(99)]],
      courseCode: ['', Validators.required],
      courseTechnologies: [[]]
    });
  }

  ngOnInit() {
    this.techService.getAllCategories().pipe(
      take(1),
      finalize(() => this.isLoading = false)
    ).subscribe(
      cats => this.availableTechCategories = cats,
      err => this.errorService.handleError(err));
  }

  onSubmit(): void {
    this.isLoading = true;
    const newCourse = {
      ...this.courseForm.value,
      courseTechnologies: []
    };
    this.courseService.saveCourse(newCourse).pipe(
      take(1),
      finalize(() => this.isLoading = false)
    ).subscribe(
      course => this.dialogRef.close(course),
      err => this.errorService.handleError(err)
    );
  }

}
