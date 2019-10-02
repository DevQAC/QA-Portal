import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { CourseService } from '../_common/services/course.service';
import { ActivatedRoute } from '@angular/router';
import { QaErrorHandlerService } from 'projects/portal-core/src/app/_common/services/qa-error-handler.service';
import { QaToastrService } from 'projects/portal-core/src/app/_common/services/qa-toastr.service';
import { CourseModel } from 'projects/portal-core/src/app/_common/models/course.model';
import { finalize } from 'rxjs/operators';

@Component({
  selector: 'app-course-detail',
  templateUrl: './course-detail.component.html',
  styleUrls: ['./course-detail.component.css']
})
export class CourseDetailComponent implements OnInit {

  public courseForm: FormGroup;

  public course: CourseModel;

  public isLoading = true;


  constructor(
    private courseService: CourseService,
    private aR: ActivatedRoute,
    private errorService: QaErrorHandlerService,
    private toastr: QaToastrService
  ) {
    this.courseForm = new FormBuilder().group({
      courseName: ['', Validators.required],
      duration: ['', [Validators.required, Validators.min(1), Validators.max(99)]],
      courseCode: ['', Validators.required]
    });
    this.courseForm.disable();

  }

  ngOnInit() {
    this.courseService.getCourseById(this.aR.snapshot.params.id)
      .pipe(finalize(() => {
        this.courseForm.enable();
        this.isLoading = false;
      }))
      .subscribe(
        course => {
          this.course = course;
          this.courseForm.patchValue(this.course);
        }, err => this.errorService.handleError(err)
      );
  }

  public onSaveCourseClicked() {
    this.course = {
      ...this.course,
      ...this.courseForm.value
    };

    this.courseService.saveCourse(this.course)
      .pipe(finalize(() => {
        this.courseForm.enable();
        this.isLoading = false;
      })).subscribe(() => {
        this.toastr.showSuccess('Course updated');
      }, err => this.errorService.handleError(err));
  }
}
