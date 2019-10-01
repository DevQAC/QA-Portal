import { Component, OnInit, ViewChild } from '@angular/core';
import { MatTableDataSource, MatDialog } from '@angular/material';
import {CohortModel} from '../../../../portal-core/src/app/_common/models/cohort.model';
import { ActivatedRoute, Router } from '@angular/router';
import { CohortService } from '../_common/services/cohort.service';
import { DataTableComponent } from 'projects/qa-common/src/app/data-table/data-table.component';
import { IRowClickEvent } from 'projects/qa-common/src/app/data-table/models/row-click-event';

@Component({
  selector: 'app-cohort-management',
  templateUrl: './cohort-management.component.html'
})
export class CohortManagementComponent implements OnInit {

  @ViewChild('dataTable', {static: false}) dataTable: DataTableComponent<CohortModel>;

  // SEARCH
  public searchInput = '';

  // TABLE
  public cohortsTableDataSource = new MatTableDataSource<CohortModel>();
  public displayedColumns = ['select', 'cohortName', 'trainer', 'start'];
  public rowSelection = [];
  public isLoading = true;

  constructor(private router: Router,
              private activatedRoute: ActivatedRoute,
              private cohortService: CohortService,
              private dialog: MatDialog) {
  }

  ngOnInit() {
    this.searchInput = this.activatedRoute.snapshot.queryParams.search || this.searchInput;
    this.performSearch();
  }

  public performSearch() {
    if (this.dataTable) { // Check if dataTable is defined. This isn't ready on first search (not that it matters)
      this.dataTable.deselectAllRows();
    }
    this.isLoading = true;
    this.router.navigate([], {relativeTo: this.activatedRoute, queryParams: {search: this.searchInput}});

    this.cohortService.searchCohorts(this.searchInput).subscribe(results => {
      this.cohortsTableDataSource.data = results;
      this.isLoading = false;
    });
  }

  public onAddCohortButtonClicked(): void {
    console.warn('Add new cohort not implemented!');
  }

  onRowClicked(event: IRowClickEvent<CohortModel>): void {
    this.router.navigate(['qa', 'portal', 'admin', 'manage', 'cohorts', event.data.id]);
  }

}
