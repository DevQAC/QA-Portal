import {Component, OnInit} from '@angular/core';
import {MatTableDataSource} from '@angular/material';
import {TrainerCourseHistoryService} from '../_common/services/trainer-course-history.service';
import {QaErrorHandlerService} from '../../../../portal-core/src/app/_common/services/qa-error-handler.service';
import {CohortCourseModel} from '../../../../portal-core/src/app/_common/models/cohort-course.model';
import {TrainerFeedbackHistoryModel} from './models/trainer-feedback-history.model';

@Component({
  selector: 'app-trainer-feedback-history',
  templateUrl: './trainer-feedback-history.component.html',
  styleUrls: ['./trainer-feedback-history.component.css']
})
export class TrainerFeedbackHistoryComponent implements OnInit {

  dataLoading = true;

  displayedColumns: string[] = ['course', 'start', 'end', 'feedback-status'];

  viewModel: TrainerFeedbackHistoryModel;

  dataSource: MatTableDataSource<CohortCourseModel>;

  constructor(private trainerCourseHistoryService: TrainerCourseHistoryService,
              private errorHandlerService: QaErrorHandlerService) { }

  ngOnInit() {
    this.trainerCourseHistoryService.getCourseHistory().subscribe(
      (response) => {
        this.viewModel = new TrainerFeedbackHistoryModel();
        this.viewModel.cohortCourses = response.cohortCourseHistory;
        this.dataSource = new MatTableDataSource(this.viewModel.cohortCourses);
        this.dataLoading = false;
      },
      (error) => {
        this.dataLoading = false;
        this.errorHandlerService.handleError(error);
      }
    );
  }

  getFeedbackUrl(id: string) {
    return '/qa/portal/training/feedback/trainer/course/' + id;
  }
}
