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
import { MatDialog } from '@angular/material';
import { AddCourseDialogComponent } from './add-course-dialog/add-course-dialog.component';
import { TrainerModel } from 'projects/portal-core/src/app/_common/models/trainer.model';
import { QaToastrService } from 'projects/portal-core/src/app/_common/services/qa-toastr.service';
import { LocationService } from '../_common/services/location.service';
import { LocationModel } from 'projects/portal-core/src/app/_common/models/location.model';
import { UserModel } from 'projects/portal-core/src/app/_common/models/user.model';
import { EditCourseDialogComponent } from './edit-course-dialog/edit-course-dialog.component';

@Component({
  selector: 'app-cohort-detail',
  templateUrl: './cohort-detail.component.html',
  styleUrls: ['./cohort-detail.component.scss']
})
export class CohortDetailComponent implements OnInit {

  public cohort: CohortModel;
  public availableTrainers: TrainerModel[] = [];
  public availableCourses: CourseModel[] = [];
  public availableLocations: LocationModel[] = [];
  public availableTrainees: UserModel[] = [];
  public calendarEvents: CalendarEvent<CohortCourseModel>[] = [];
  public viewDate: Date = new Date();
  public refreshCalendar = new Subject<any>();
  public cohortForm: FormGroup;
  public isLoading = true;



  constructor(
    private cohortService: CohortService,
    private courseService: CourseService,
    private locationService: LocationService,
    private aR: ActivatedRoute,
    private dialog: MatDialog,
    private toastr: QaToastrService,
    private errorService: QaErrorHandlerService) {
    // this.availableEvents = this.availableCourses.map(course => this.courseToCalendarEvent(course));
    this.cohortForm = new FormBuilder().group({
      name: [''],
      startDate: [''],
      trainerUserName: [''],
      traineeNames: [[]]
    });
    this.cohortForm.disable();
  }

  ngOnInit() {
    const cohortId = this.aR.snapshot.params.id;
    forkJoin(
      this.cohortService.getCohortById(cohortId),
      this.courseService.getAllCourses(),
      this.cohortService.getAvailableTrainersForCohort(),
      this.locationService.getAllLocations(),
      this.cohortService.getAvailableTraineesByCohortId(cohortId)
    ).pipe(take(1))
      .subscribe(([cohort, courses, trainers, locations, trainees]) => {
        this.cohort = cohort;
        this.availableCourses = courses;
        this.availableTrainers = trainers;
        this.availableLocations = locations;
        this.availableTrainees = trainees;
        this.calendarEvents = this.cohort.cohortCourses.map(c => this.cohortCourseToCalendarEvent(c));
        this.viewDate = moment(cohort.startDate).toDate();

        this.cohortForm.patchValue(cohort);
        this.isLoading = false;
        this.cohortForm.enable();
      },
        (err) => this.errorService.handleError(err)
      );

    this.cohortForm.valueChanges.subscribe(v => {
      this.cohort = { ...this.cohort, ...v };
    });
  }

  private cohortCourseToCalendarEvent(course: CohortCourseModel): CalendarEvent<CohortCourseModel> {
    const startMom = moment(course.startDate).utc().add(moment(course.startDate).utcOffset(), 'm');

    return {
      start: startMom.toDate(),
      end: course.endDate ? moment(course.endDate).utc().add(moment(course.startDate).utcOffset(), 'm').toDate() : startMom.add(course.course.duration, 'days').toDate(),
      title: course.course.courseName,
      draggable: true,
      allDay: true,
      color: this.courseService.getColorForCourse(course.course),
      meta: course
    };
  }

  private calendarEventToCohortCourse(calendarEvent: CalendarEvent<CohortCourseModel>): CohortCourseModel {
    return {
      ...calendarEvent.meta,
      startDate: moment(calendarEvent.start).format('YYYY-MM-DD'),
      endDate: moment(calendarEvent.end).format('YYYY-MM-DD')
    };
  }

  private buildCohortCourse(course: CourseModel, startDate: Date, endDate: Date, trainer: UserModel, location: LocationModel): CohortCourseModel {
    return {
      course, startDate, endDate, trainer, location
    } as CohortCourseModel;
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

  public eventClicked({ event, ...rest }: { event: CalendarEvent }): void {
    const dialog = this.dialog.open(EditCourseDialogComponent,
      {
        data: {
          availableCourses: this.availableCourses,
          availableTrainers: this.availableTrainers,
          availableLocations: this.availableLocations,
          meta: event.meta
        }
      }
    );

    dialog.afterClosed().subscribe(course => {
      if (course) {
        if (course.remove) {
          this.calendarEvents = this.calendarEvents.filter(e => e.meta.id !== event.meta.id);
        } else {
          event.meta = course.meta;
          event.start = event.meta.startDate;
          event.end = event.meta.endDate;
        }

        this.refreshCalendar.next();

      }
    });
  }

  public dayClicked({ day }): void {
    const dialog = this.dialog.open(
      AddCourseDialogComponent,
      {
        data: {
          availableCourses: this.availableCourses,
          availableTrainers: this.availableTrainers,
          availableLocations: this.availableLocations
        }
      }
    );

    dialog.beforeClosed().subscribe(data => {
      if (data) {
        this.calendarEvents.push(
          this.cohortCourseToCalendarEvent(
            this.buildCohortCourse(
              data.selectedCourse,
              day.date,
              moment(day.date).add((data.selectedDuration || 1) - 1, 'days').toDate(),
              data.selectedTrainer,
              data.selectedLocation
            )
          )
        );
        this.refreshCalendar.next();
      }
    });
  }


  public onSaveCohortClicked(): void {
    this.cohort = {
      ...this.cohort,
      ...this.cohortForm.value,
      cohortCourses: this.calendarEvents.map(e => this.calendarEventToCohortCourse(e))
    };

    this.cohortService.saveCohort(this.cohort).subscribe(resp => {
      this.toastr.showSuccess('Cohort updated');
    },
      err => this.errorService.handleError(err));
  }
}
