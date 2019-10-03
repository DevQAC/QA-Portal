import {Component, Input, OnChanges} from '@angular/core';
import {MatTableDataSource} from '@angular/material';
import {CohortSummaryModel} from '../../_common/models/cohort-summary.model';

@Component({
  selector: 'app-cohort-table',
  templateUrl: './cohort-table.component.html',
  styleUrls: ['./cohort-table.component.css']
})
export class CohortTableComponent implements OnChanges {
  @Input() cohortSummaryData: CohortSummaryModel[];

  dataSource: MatTableDataSource<CohortSummaryModel>;

  /** Columns displayed in the table. Columns IDs can be added, removed, or reordered. */
  displayedColumns = ['cohortName', 'week1', 'week2', 'week3', 'week4', 'week5', 'week6', 'week7', 'week8', 'week9', 'week10', 'week11', 'week12'];

  ngOnChanges(): void {
    this.dataSource = new MatTableDataSource<CohortSummaryModel>(this.cohortSummaryData);
  }
}
