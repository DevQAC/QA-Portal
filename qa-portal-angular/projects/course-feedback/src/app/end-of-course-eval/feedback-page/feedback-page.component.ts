import {Component, OnDestroy, OnInit} from '@angular/core';
import {take} from 'rxjs/operators';
import {EvaluationService} from '../../_common/services/evaluation-service';
import {QaErrorHandlerService} from '../../../../../portal-core/src/app/_common/services/qa-error-handler.service';
import {Subscription} from 'rxjs';
import { IFormModel } from 'projects/qa-forms/src/app/_common/models';

@Component({
  selector: 'app-feedback-page',
  templateUrl: './feedback-page.component.html',
  styleUrls: ['./feedback-page.component.css']
})
export class FeedbackPageComponent implements OnInit, OnDestroy {

  dataLoaded = false;

  getCurrentEvaluationSubscription: Subscription;
  createEvaluationSubscription: Subscription;
  updateEvaluationSubscription: Subscription;

  viewModel: IFormModel;

  constructor(private evaluationService: EvaluationService,
              private errorHandlerService: QaErrorHandlerService) {
  }

  ngOnInit() {
    this.evaluationService.getCurrentTraineeEvaluation().subscribe(
      (response) => {
        this.viewModel = response;
        this.dataLoaded = true;
      },
      (error) => {
        this.dataLoaded = true;
        this.errorHandlerService.handleError(error);
      }
    );
  }

  ngOnDestroy(): void {
    if (!!this.getCurrentEvaluationSubscription) {
      this.getCurrentEvaluationSubscription.unsubscribe();
    }

    if (!!this.createEvaluationSubscription) {
      this.createEvaluationSubscription.unsubscribe();
    }

    if (!!this.updateEvaluationSubscription) {
      this.updateEvaluationSubscription.unsubscribe();
    }
  }

  /**
   * This method will submit the current state of the form.
   * @method onFeedbackSubmit
   * @memberof FeedbackPageComponent
   */
  onFeedbackSubmit() {
    this.evaluationService.createEvaluationForm(this.viewModel)
      .pipe(take(1))
      .subscribe((response) => {
          // Navigate to the Evaluation history page for trainee
          console.log(response);
        },
        (error) => {
          console.log(error);
          this.errorHandlerService.handleError(error);
        }
      );
  }
}
