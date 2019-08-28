import {Component, OnDestroy, OnInit} from '@angular/core';
import {TraineeEvaluationSummaryService} from './services/trainee-evaluation-summary.service';
import {TraineeEvaluationSummaryModel} from './models/trainee-evaluation-summary.model';
import {Subscription} from 'rxjs';
import {QaErrorHandlerService} from '../../../../portal-core/src/app/_common/services/qa-error-handler.service';
import {MatTableDataSource} from '@angular/material';
import {TraineeEvaluationSummaryRowModel} from './models/trainee-evaluation-summary-row.model';

@Component({
  selector: 'app-trainee-evaluation-summary',
  templateUrl: './trainee-evaluation-summary.component.html',
  styleUrls: ['./trainee-evaluation-summary.component.css']
})
export class TraineeEvaluationSummaryComponent implements OnInit, OnDestroy {

  dataLoading = true;

  viewModel: TraineeEvaluationSummaryModel;

  getEvaluationSummarySubscription: Subscription;

  displayedColumns: string[] = ['course', 'start', 'end', 'courseid', 'status'];

  dataSource: MatTableDataSource<TraineeEvaluationSummaryRowModel>;

  constructor(private traineeEvaluationSummaryService: TraineeEvaluationSummaryService,
              private errorHandlerService: QaErrorHandlerService) {
  }

  ngOnInit() {
    this.traineeEvaluationSummaryService.getTraineeEvaluationSummary().subscribe((response) => {
        this.viewModel = response;
        this.dataSource = new MatTableDataSource<TraineeEvaluationSummaryRowModel>(this.viewModel.evaluationSummaryRows);
        this.dataLoading = false;
      },
      (error) => {
        this.dataLoading = false;
        this.errorHandlerService.handleError(error);
      });
  }

  ngOnDestroy(): void {
    if (!!this.getEvaluationSummarySubscription) {
      this.getEvaluationSummarySubscription.unsubscribe();
    }
  }

  getEvaluationUrl(cohortCourseId: string): string {
    return '/qa/portal/training/feedback/trainee/evaluation/' + cohortCourseId;
  }
}
