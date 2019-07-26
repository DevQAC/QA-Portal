import {Component, OnInit} from '@angular/core';
import {MenuService} from '../../../../portal-core/src/app/_common/services/menu-service';
import {SummaryService} from './services/summary.service';

@Component({
  selector: 'app-cohort-summary',
  templateUrl: './cohort-summary.component.html',
  styleUrls: ['./cohort-summary.component.css']
})
export class CohortSummaryComponent implements OnInit {

  constructor(private summaryService: SummaryService) {
  }

  summary: any[] = [];

  ngOnInit() {
    this.summaryService.getSummary().subscribe((response) => {
      this.summary = response;
    });
  }

}
