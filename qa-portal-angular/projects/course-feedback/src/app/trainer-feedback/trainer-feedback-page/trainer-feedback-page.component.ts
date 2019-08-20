import {Component, OnDestroy, OnInit} from '@angular/core';
import {FeedbackService} from '../_common/services/feedback.service';
import {QaErrorHandlerService} from '../../../../../portal-core/src/app/_common/services/qa-error-handler.service';
import {FeedbackFormModel} from '../../_common/models/feedback-form.model';
import {Subscription} from 'rxjs';
import {ICategory} from '../../../../../qa-forms/src/app/_common/models/form-category.model';

@Component({
  selector: 'app-trainer-feedback-page',
  templateUrl: './trainer-feedback-page.component.html',
  styleUrls: ['./trainer-feedback-page.component.css']
})
export class TrainerFeedbackPageComponent implements OnInit, OnDestroy {

  dataLoaded = false;

  getFeedbackSubscription: Subscription;

  createFeedbackSubscription: Subscription;

  updateFeedbackSubscription: Subscription;

  viewModel: FeedbackFormModel;

  constructor(private feedbackService: FeedbackService,
              private errorHandlerService: QaErrorHandlerService) {
  }

  ngOnInit() {
    this.getFeedbackSubscription = this.feedbackService.getFeedbackforCourse(1).subscribe((response: FeedbackFormModel) => {
        this.viewModel = response;
        this.dataLoaded = true;
      },
      (error) => {
        console.log(error);
        this.dataLoaded = true;
        this.errorHandlerService.handleError(error);
      });
  }

  modelChanged(event: ICategory) {
  }

  saveFeedback() {
    this.createFeedbackSubscription = this.feedbackService.createFeedbackForm(this.viewModel).subscribe((response) => {
        // Navigate to the feedback history page for the trainer
      },
      (error) => {
        this.errorHandlerService.handleError(error);
      });
    console.warn(this.viewModel);
  }

  ngOnDestroy(): void {
    if (!!this.getFeedbackSubscription) {
      this.getFeedbackSubscription.unsubscribe();
    }

    if (!!this.createFeedbackSubscription) {
      this.createFeedbackSubscription.unsubscribe();
    }
  }
}
