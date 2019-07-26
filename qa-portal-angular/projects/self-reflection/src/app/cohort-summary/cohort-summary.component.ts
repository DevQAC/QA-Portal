import {Component, OnDestroy, OnInit} from '@angular/core';
import {SummaryService} from './services/summary.service';
import {MatTableDataSource} from '@angular/material';
import {Subscription} from 'rxjs';

export interface Summary {
  cohortName: string;
  scores: number[];
}

@Component({
  selector: 'app-cohort-summary',
  templateUrl: './cohort-summary.component.html',
  styleUrls: ['./cohort-summary.component.css']
})
export class CohortSummaryComponent implements OnInit, OnDestroy {

  constructor(private summaryService: SummaryService) {
  }

  columnsToDisplay: string[] = ['name', 'wk1', 'wk2', 'wk3', 'wk4', 'wk5', 'wk6', 'wk7', 'wk8', 'wk9', 'wk10', 'wk11', 'wk12'];
  summary: MatTableDataSource<Summary>;
  sub: Subscription;

  ngOnInit(): void {
    this.sub = this.summaryService.getSummary().subscribe((response: Summary[]) => {
      this.summary = new MatTableDataSource(response);
    });
  }

  ngOnDestroy(): void {
    this.sub.unsubscribe();
  }

}
