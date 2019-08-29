import {Component, OnDestroy, OnInit} from '@angular/core';
import {QaErrorHandlerService} from '../../../../portal-core/src/app/_common/services/qa-error-handler.service';
import {Subscription} from 'rxjs';
import {ICategoryResponse, IFormModel} from 'projects/qa-forms/src/app/_common/models';
import {FeedbackService} from './_common/services/feedback.service';
import {ActivatedRoute, ParamMap, Router} from '@angular/router';

@Component({
  selector: 'app-trainer-feedback-page',
  templateUrl: './trainer-feedback-page.component.html',
  styleUrls: ['./trainer-feedback-page.component.css']
})
export class TrainerFeedbackPageComponent implements OnInit, OnDestroy {

  summaryUrl = 'qa/portal/training/feedback/trainer/history';

  dataLoaded = false;

  getFormSubscription: Subscription;

  createFormSubscription: Subscription;

  updateFormSubscription: Subscription;

  viewModel: IFormModel;

  constructor(private feedbackService: FeedbackService,
              private errorHandlerService: QaErrorHandlerService,
              private router: Router,
              private route: ActivatedRoute) {
  }

  ngOnInit() {
    this.route.paramMap.subscribe(
      (params: ParamMap) => {
        this.getFeedbackForCourse(params.get('id'));
      });
  }

  ngOnDestroy(): void {
    this.unsubscribe(this.getFormSubscription);
    this.unsubscribe(this.createFormSubscription);
    this.unsubscribe(this.updateFormSubscription);
  }

  getFeedbackForCourse(id: string) {
    this.getFormSubscription = this.feedbackService.getFeedbackforCourse(id)
      .subscribe(
        (response) => {
        this.viewModel = response;
        this.dataLoaded = true;
      },
      (error) => {
        this.dataLoaded = true;
        this.errorHandlerService.handleError(error);
      });
  }

  onSubmit() {
    this.setFormStatus();
    if (this.isNewForm()) {
      this.createForm();
    } else {
      this.updateForm();
    }
  }

  private createForm(): void {
    this.createFormSubscription = this.feedbackService.createFeedbackForm(this.viewModel)
      .subscribe((response) => {
          // Navigate to the Evaluation history page for trainee
          this.navigateToSummary(this.summaryUrl);
        },
        (error) => {
          this.errorHandlerService.handleError(error);
        }
      );
  }

  private updateForm(): void {
    this.updateFormSubscription = this.feedbackService.updateFeedbackForm(this.viewModel)
      .subscribe((response) => {
          // Navigate to the Evaluation history page for trainee
          this.navigateToSummary(this.summaryUrl);
        },
        (error) => {
          this.errorHandlerService.handleError(error);
        }
      );
  }

  private setFormStatus(): void {
    this.viewModel.status = this.formCompleted() ? 'Submitted' : 'Saved';
  }

  private isNewForm(): boolean {
    return !this.viewModel.id;
  }

  private formCompleted(): boolean {
    const incompleteQuestionCategory = this.viewModel.categoryResponses
      .find(cr => {
        return !this.questionsAnswered(cr);
      });
    return !incompleteQuestionCategory;
  }

  private questionsAnswered(categoryResponse: ICategoryResponse): boolean {
    const questionResponse = categoryResponse.questionResponses.find(qr => {
      return !qr.responseValues || qr.responseValues.length === 0;
    });
    return !questionResponse;
  }

  private navigateToSummary(url: string): void {
    this.router.navigateByUrl(url);
  }

  private unsubscribe(subscription: Subscription) {
    if (!!subscription) {
      subscription.unsubscribe();
    }
  }
}
