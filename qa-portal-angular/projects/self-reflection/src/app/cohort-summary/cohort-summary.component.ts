import {Component, OnDestroy, OnInit} from '@angular/core';
import {SummaryService} from './services/summary.service';
import {Observable, Subscription} from 'rxjs';
import {CohortSummaryModel} from '../_common/models/cohort-summary.model';
import {QaErrorHandlerService} from '../../../../portal-core/src/app/_common/services/qa-error-handler.service';

@Component({
  selector: 'app-cohort-summary',
  templateUrl: './cohort-summary.component.html',
  styleUrls: ['./cohort-summary.component.css']
})
export class CohortSummaryComponent implements OnInit, OnDestroy {

  loadingData = true;

  summarySubscription: Subscription;

  cohortSummary$: Observable<CohortSummaryModel[]>;

  constructor(private summaryService: SummaryService,
              private errorHandler: QaErrorHandlerService) {
  }

  ngOnInit() {
    this.cohortSummary$ = this.summaryService.getSummary();
    this.summarySubscription = this.cohortSummary$.subscribe((summaries) => {
        this.loadingData = false;
      },
      (error) => {
        this.loadingData = false;
        this.errorHandler.handleError(error);
      });
  }

  ngOnDestroy() {
    this.summarySubscription.unsubscribe();
  }
}
