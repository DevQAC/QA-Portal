import { Component, OnInit } from '@angular/core';
import { CohortModel } from 'projects/portal-core/src/app/_common/models/cohort.model';
import { CourseModel } from 'projects/portal-core/src/app/_common/models/course.model';
import { CdkDragDrop, moveItemInArray, transferArrayItem } from '@angular/cdk/drag-drop';
import { CalendarEvent } from 'calendar-utils';

import * as moment from 'moment';
import business from 'moment-business';
import { CohortService } from '../_common/services/cohort.service';
import { forkJoin, Subject } from 'rxjs';
import { take } from 'rxjs/operators';
import { FormGroup, FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { QaErrorHandlerService } from 'projects/portal-core/src/app/_common/services/qa-error-handler.service';
import { CourseService } from '../_common/services/course.service';
import { CohortCourseModel } from 'projects/portal-core/src/app/_common/models/cohort-course.model';
import { CalendarEventTimesChangedEvent } from 'angular-calendar';

@Component({
  selector: 'app-cohort-detail',
  templateUrl: './cohort-detail.component.html',
  styleUrls: ['./cohort-detail.component.scss']
})
export class CohortDetailComponent implements OnInit {

  public viewDate: Date = new Date();
  public calendarEvents: CalendarEvent<CohortCourseModel>[] = [
  ];

  public cohort: CohortModel;

  public availableCourses: CourseModel[] = [];

  public selectedCourses: CourseModel[] = [
  ];

  public cohortForm: FormGroup;
  public isLoading = true;
  public refreshCalendar = new Subject<any>();

  constructor(
    private cohortService: CohortService,
    private courseService: CourseService,
    private aR: ActivatedRoute,
    private errorService: QaErrorHandlerService) {
    // this.availableEvents = this.availableCourses.map(course => this.courseToCalendarEvent(course));
    this.cohortForm = new FormBuilder().group({
      name: [''],
      startDate: ['']
    });
    this.cohortForm.disable();
  }

  ngOnInit() {
    forkJoin(
      this.cohortService.getCohortById(this.aR.snapshot.params.id),
      this.courseService.getAllCourses()
    ).pipe(take(1))
      .subscribe(([cohort, courses]) => {
        this.cohort = cohort;
        this.availableCourses = courses;
        this.calendarEvents = this.cohort.cohortCourses.map(c => this.courseToCalendarEvent(c));

        this.viewDate = moment(cohort.startDate).toDate();

        this.cohortForm.patchValue(cohort);
        this.isLoading = false;
        this.cohortForm.enable();
        // debugger;
      },
        (err) => this.errorService.handleError(err)
      );

    this.cohortForm.valueChanges.subscribe(v => {
      this.cohort = { ...this.cohort, ...v };
    });
  }

  private courseToCalendarEvent(course: CohortCourseModel): CalendarEvent<CohortCourseModel> {
    return {
      start: moment(course.startDate).toDate(),
      end: course.endDate ? moment(course.endDate).toDate() : moment(course.startDate).add(course.course.duration, 'days').toDate(),
      title: course.course.courseName,
      draggable: true,
      allDay: true,
      color: this.courseService.getColorForCourse(course.course),
      meta: course
    };
  }


  public eventTimesChanged({ event, newStart, newEnd }: CalendarEventTimesChangedEvent): void {
    event.start = newStart;
    event.end = newEnd;
    this.refreshCalendar.next();
  }

  public onCalendarStartClicked(): void {
    this.viewDate = moment(this.cohort.startDate).toDate();
    this.refreshCalendar.next();
  }
}
