import {Component, OnInit} from '@angular/core';
import {FormGroup, FormBuilder, Validators} from '@angular/forms';
import {CourseService} from '../_common/services/course.service';
import {ActivatedRoute} from '@angular/router';
import {QaErrorHandlerService} from 'projects/portal-core/src/app/_common/services/qa-error-handler.service';
import {QaToastrService} from 'projects/portal-core/src/app/_common/services/qa-toastr.service';
import {CourseModel} from 'projects/portal-core/src/app/_common/models/course.model';
import {finalize} from 'rxjs/operators';
import {forkJoin} from 'rxjs';
import {TechnologyService} from '../_common/technology.service';
import {TechnologyCategoryModel} from 'projects/portal-core/src/app/_common/models/technology-category.model';
import {TechnologyModel} from 'projects/portal-core/src/app/_common/models/technology.model';
import {CourseTechnologyModel} from 'projects/portal-core/src/app/_common/models/course-technology.model';

@Component({
  selector: 'app-course-detail',
  templateUrl: './course-detail.component.html',
  styleUrls: ['./course-detail.component.css']
})
export class CourseDetailComponent implements OnInit {

  public courseForm: FormGroup;

  public course: CourseModel;

  public availableTechCategories: TechnologyCategoryModel[] = [];

  public isLoading = true;

  constructor(
    private courseService: CourseService,
    private techService: TechnologyService,
    private aR: ActivatedRoute,
    private errorService: QaErrorHandlerService,
    private toastr: QaToastrService
  ) {
    this.courseForm = new FormBuilder().group({
      courseName: ['', Validators.required],
      duration: ['', [Validators.required, Validators.min(1), Validators.max(99)]],
      courseCode: ['', Validators.required],
      courseTechnologies: [[]]
    });
    this.courseForm.disable();

  }

  ngOnInit() {
    forkJoin(
      this.courseService.getCourseById(this.aR.snapshot.params.id),
      this.techService.getAllCategories()
    )
      .pipe(finalize(() => {
        this.courseForm.enable();
        this.isLoading = false;
      }))
      .subscribe(
        ([course, techCats]) => {
          this.course = course;
          this.availableTechCategories = techCats;
          this.courseForm.patchValue({
            ...this.course,
            courseTechnologies: this.course.courseTechnologies.map(c => c.technology.id)
          });
        }, err => this.errorService.handleError(err)
      );
  }

  public onSaveCourseClicked() {
    this.course = {
      ...this.course,
      ...this.courseForm.value,
      courseTechnologies: this.techIdArrayToCourseTechArray(this.courseForm.value.courseTechnologies)
    };

    this.courseService.saveCourse(this.course)
      .pipe(finalize(() => {
        this.courseForm.enable();
        this.isLoading = false;
      })).subscribe(() => {
      this.toastr.showSuccess('Course updated');
    }, err => this.errorService.handleError(err));
  }

  private techIdArrayToCourseTechArray(ids: number[]): CourseTechnologyModel[] {
    return this.availableTechCategories
      .reduce<TechnologyModel[]>((prev, curr) => [...prev, ...curr.technologies], [])
      .filter(val => ids.some(id => id === val.id))
      .map(tech => ({technology: tech} as CourseTechnologyModel));
  }
}
