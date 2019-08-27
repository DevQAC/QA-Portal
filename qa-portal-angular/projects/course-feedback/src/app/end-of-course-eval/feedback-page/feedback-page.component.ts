import {Component, OnDestroy, OnInit} from '@angular/core';
import {EvaluationService} from '../../_common/services/evaluation-service';
import {QaErrorHandlerService} from '../../../../../portal-core/src/app/_common/services/qa-error-handler.service';
import {Subscription} from 'rxjs';
import {ICategoryResponse, IFormModel} from 'projects/qa-forms/src/app/_common/models';
import {ActivatedRoute, ParamMap, Router} from '@angular/router';

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
              private errorHandlerService: QaErrorHandlerService,
              private router: Router,
              private route: ActivatedRoute) {
  }

  updatedModel(event: IFormModel) {
    // this.viewModel = event;
  }

  ngOnInit() {
    // Get trainee course evaluation for the supplied cohort course id
    this.route.paramMap.subscribe(
      (params: ParamMap) => {
        this.getEvaluationForCohortCourse(params.get('id'));
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
    this.setEvaluationStatus();
    if (this.isNewEvaluation()) {
      this.createEvaluationForm();
    } else {
      this.updateEvaluationForm();
    }
  }

  private createEvaluationForm(): void {
    this.createEvaluationSubscription = this.evaluationService.createEvaluationForm(this.viewModel)
      .subscribe((response) => {
          // Navigate to the Evaluation history page for trainee
          this.navigateToEvaluationSummary();
        },
        (error) => {
          this.errorHandlerService.handleError(error);
        }
      );
  }

  private updateEvaluationForm(): void {
    this.updateEvaluationSubscription = this.evaluationService.updateEvaluationForm(this.viewModel)
      .subscribe((response) => {
          // Navigate to the Evaluation history page for trainee
          this.navigateToEvaluationSummary();
        },
        (error) => {
          this.errorHandlerService.handleError(error);
        }
      );
  }

  private getEvaluationForCohortCourse(cohortCourseId: string) {
    this.getCurrentEvaluationSubscription = this.evaluationService.getEvaluationForTraineeAndCohortCourse(cohortCourseId).subscribe(
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

  private setEvaluationStatus(): void {
    this.viewModel.status = this.allCategoryQuestionsAnswered() ? 'Submitted' : 'Saved';
  }

  private isNewEvaluation(): boolean {
    return !this.viewModel.id;
  }

  private allCategoryQuestionsAnswered(): boolean {
    const incompleteQuestionCategory = this.viewModel.categoryResponses
      .find(cr => {
        console.log('SCOTT-FORM Category is ' + cr.questionCategory.categoryName);
        return !this.questionsAnswered(cr);
      });
    return !incompleteQuestionCategory;
  }

  private questionsAnswered(categoryResponse: ICategoryResponse): boolean {
    const questionResponse = categoryResponse.questionResponses.find(qr => {
      console.log('SCOTT-FORM Question ' + qr.question.body);
      console.log('SCOTT-FORM Question response ' + JSON.stringify(qr.responseValues));
      return !qr.responseValues || qr.responseValues.length === 0;
    });
    return !questionResponse;
  }

  private navigateToEvaluationSummary(): void {
    this.router.navigateByUrl('qa/portal/training/feedback/trainee/evaluation/history');
  }
}
