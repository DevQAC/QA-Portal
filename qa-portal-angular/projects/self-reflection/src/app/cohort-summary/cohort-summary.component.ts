import {Component, OnDestroy, OnInit} from '@angular/core';
import {SummaryService} from './services/summary.service';
import {Observable, Subscription} from 'rxjs';
import {CohortSummaryModel} from '../_common/models/cohort-summary.model';

@Component({
  selector: 'app-cohort-summary',
  templateUrl: './cohort-summary.component.html',
  styleUrls: ['./cohort-summary.component.css']
})
export class CohortSummaryComponent implements OnInit, OnDestroy {

  loadingData = true;

  summarySubscription: Subscription;

  cohortSummary$: Observable<CohortSummaryModel[]>;

  constructor(private summaryService: SummaryService) { }

  ngOnInit() {
    this.cohortSummary$ = this.summaryService.getSummary();
    this.summarySubscription = this.cohortSummary$.subscribe((summaries) => {
      console.log(summaries.length);
      this.loadingData = false;
    });
  }

  ngOnDestroy() {
    this.summarySubscription.unsubscribe();
  }
}
