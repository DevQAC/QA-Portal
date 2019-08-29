import { Component, OnDestroy, OnInit } from '@angular/core';
import { QaErrorHandlerService } from '../../../../portal-core/src/app/_common/services/qa-error-handler.service';
import { Subscription } from 'rxjs';
import { IFormModel } from 'projects/qa-forms/src/app/_common/models';
import {FeedbackService} from './_common/services/feedback.service';

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

  viewModel: IFormModel;

  constructor(private feedbackService: FeedbackService,
              private errorHandlerService: QaErrorHandlerService) {
  }

  ngOnInit() {
    this.getFeedbackSubscription = this.feedbackService.getFeedbackforCourse(1).subscribe(response => {
      this.viewModel = response;
      this.dataLoaded = true;
    },
      (error) => {
        this.dataLoaded = true;
        this.errorHandlerService.handleError(error);
      });
  }

  saveFeedback() {
    this.createFeedbackSubscription = this.feedbackService.createFeedbackForm(this.viewModel).subscribe(response => {
      // Navigate to the feedback history page for the trainer
    },
      (error) => {
        this.errorHandlerService.handleError(error);
      });
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
