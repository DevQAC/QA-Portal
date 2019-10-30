import { Component, OnInit, ViewChild } from '@angular/core';
import { MatTableDataSource, MatDialog } from '@angular/material';
import { CohortModel } from '../../../../portal-core/src/app/_common/models/cohort.model';
import { ActivatedRoute, Router } from '@angular/router';
import { CohortService } from '../_common/services/cohort.service';
import { DataTableComponent } from 'projects/qa-common/src/app/data-table/data-table.component';
import { IRowClickEvent } from 'projects/qa-common/src/app/data-table/models/row-click-event';
import { NewCohortDialogComponent } from './new-cohort-dialog/new-cohort-dialog.component';
import { QaErrorHandlerService } from 'projects/portal-core/src/app/_common/services/qa-error-handler.service';

@Component({
  selector: 'app-cohort-management',
  templateUrl: './cohort-management.component.html'
})
export class CohortManagementComponent implements OnInit {

  @ViewChild('dataTable', { static: false }) dataTable: DataTableComponent<CohortModel>;

  // SEARCH
  public searchInput = '';

  // TABLE
  public cohortsTableDataSource = new MatTableDataSource<CohortModel>();
  public displayedColumns = ['cohortName', 'trainer', 'start'];
  public rowSelection = [];
  public isLoading = true;

  constructor(
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private cohortService: CohortService,
    private errorService: QaErrorHandlerService,
    private dialog: MatDialog) { }

  ngOnInit() {
    this.searchInput = this.activatedRoute.snapshot.queryParams.search || this.searchInput;
    this.performSearch();
  }

  public performSearch() {
    if (this.dataTable) { // Check if dataTable is defined. This isn't ready on first search (not that it matters)
      this.dataTable.deselectAllRows();
    }
    this.isLoading = true;
    this.router.navigate([], { relativeTo: this.activatedRoute, queryParams: { search: this.searchInput } });

    this.cohortService.searchCohorts(this.searchInput).subscribe(results => {
      this.cohortsTableDataSource.data = results;
      this.isLoading = false;
    }, err => this.errorService.handleError(err));
  }

  public onAddCohortButtonClicked(): void {
    this.dialog.open(NewCohortDialogComponent, {}).afterClosed().subscribe(data => {
      if (data) {
        this.performSearch();
      }
    });
  }

  onRowClicked(event: IRowClickEvent<CohortModel>): void {
    this.router.navigate(['qa', 'portal', 'admin', 'manage', 'cohorts', event.data.id]);
  }

}
