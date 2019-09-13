import {Component, OnDestroy, OnInit} from '@angular/core';
import {QaErrorHandlerService} from '../../../../portal-core/src/app/_common/services/qa-error-handler.service';
import {FeedbackService} from './_common/services/feedback.service';
import {ActivatedRoute, Router} from '@angular/router';
import {FormPage} from '../_common/form-page';

@Component({
  selector: 'app-trainer-feedback-page',
  templateUrl: './trainer-feedback-page.component.html',
  styleUrls: ['./trainer-feedback-page.component.css']
})
export class TrainerFeedbackPageComponent extends FormPage implements OnInit, OnDestroy {

  constructor(feedbackService: FeedbackService,
              errorHandlerService: QaErrorHandlerService,
              router: Router,
              route: ActivatedRoute) {
    super(feedbackService, errorHandlerService, router, route, 'qa/portal/training/feedback/trainer/history');
  }

  ngOnInit() {
    this.initialiseForm();
  }

  ngOnDestroy(): void {
    this.clearSubscriptions();
  }
}
