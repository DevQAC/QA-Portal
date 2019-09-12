import {Component, OnInit} from '@angular/core';

import {QaErrorHandlerService} from 'projects/portal-core/src/app/_common/services/qa-error-handler.service';
import {RetrieveTrainerEvaluationHistoryService} from './services/retrieve-trainer-evaluation-history.service';
import {MatTableDataSource} from '@angular/material';
import {TrainerCourseHistoryModel} from './models/trainer-course.history.model';
import {CohortCourseModel} from '../../../../portal-core/src/app/_common/models/cohort-course.model';

@Component({
  selector: 'app-trainer-evaluation-history',
  templateUrl: './trainer-evaluation-history.component.html',
  styleUrls: ['./trainer-evaluation-history.component.css']
})
export class TrainerEvaluationHistoryComponent implements OnInit {

  currentCourseDataSource: MatTableDataSource<CohortCourseModel>;

  prevCoursesDataSource: MatTableDataSource<CohortCourseModel>;

  trainerEvalHistory: MatTableDataSource<any>;

  dataLoading = true;

  constructor(private retrieveTrainerEvalHistory: RetrieveTrainerEvaluationHistoryService,
              private errorHandler: QaErrorHandlerService) {
  }

  ngOnInit() {
    this.retrieveTrainerEvalHistory.getEvalHistory().subscribe(
      (response: TrainerCourseHistoryModel) => {
        this.populateTables(response);
      },
      (error) => {
        this.dataLoading = false;
        this.errorHandler.handleError(error);
      });
  }

  populateTables(response: TrainerCourseHistoryModel) {
    if (!!response.currentCohortCourse) {
      this.currentCourseDataSource = new MatTableDataSource<CohortCourseModel>([response.currentCohortCourse]);
    } else {
      this.currentCourseDataSource = new MatTableDataSource<CohortCourseModel>([]);
    }
    this.prevCoursesDataSource = new MatTableDataSource<CohortCourseModel>(response.previousCohortCourses);
    this.trainerEvalHistory = new MatTableDataSource<any>([{knowledge: response.averageKnowledgeRating, tqi: response.averageTqiRating}])
    this.dataLoading = false;
  }
}
