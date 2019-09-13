import {Component, OnDestroy, OnInit} from '@angular/core';
import {EvaluationService} from '../_common/services/evaluation-service';
import {QaErrorHandlerService} from '../../../../portal-core/src/app/_common/services/qa-error-handler.service';
import {ActivatedRoute, Router} from '@angular/router';
import {FormPage} from '../_common/form-page';

@Component({
  selector: 'app-trainee-course-evaluation',
  templateUrl: './trainee-course-evaluation.component.html',
  styleUrls: ['./trainee-course-evaluation.component.css']
})
export class TraineeCourseEvaluationComponent extends FormPage implements OnInit, OnDestroy {

  constructor(evaluationService: EvaluationService,
              errorHandlerService: QaErrorHandlerService,
              router: Router,
              route: ActivatedRoute) {
    super(evaluationService, errorHandlerService, router, route, 'qa/portal/training/feedback/trainee/evaluation/history');
  }

  ngOnInit() {
    this.initialiseForm();
  }

  ngOnDestroy(): void {
    this.clearSubscriptions();
  }
}
