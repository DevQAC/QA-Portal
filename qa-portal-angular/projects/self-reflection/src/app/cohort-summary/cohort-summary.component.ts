import { Component, OnInit } from '@angular/core';
import {SummaryService} from './services/summary.service';
import {Observable} from 'rxjs';
import {CohortSummaryModel} from '../_common/models/cohort-summary.model';

@Component({
  selector: 'app-cohort-summary',
  templateUrl: './cohort-summary.component.html',
  styleUrls: ['./cohort-summary.component.css']
})
export class CohortSummaryComponent implements OnInit {

  cohortSummary$: Observable<CohortSummaryModel[]>;

  constructor(private summaryService: SummaryService) { }

  ngOnInit() {
    this.cohortSummary$ = this.summaryService.getSummary();
  }
}
