import { Component, OnInit, OnChanges, ViewChild } from '@angular/core';
import { MatTableDataSource, MatCheckboxChange, MatTable, MatDialog } from '@angular/material';
import { ICohort } from '../_common/models/cohort.model';
import { ActivatedRoute, Router } from '@angular/router';
import { CohortService } from '../_common/services/cohort.service';
import { DeleteCohortDialogComponent } from './delete-cohort-dialog/delete-cohort-dialog.component';
import { DataTableComponent } from 'projects/qa-common/src/app/data-table/data-table.component';

@Component({
  selector: 'app-cohort-management',
  templateUrl: './cohort-management.component.html'
})
export class CohortManagementComponent implements OnInit {

  @ViewChild('dataTable', { static: false }) dataTable: DataTableComponent<ICohort>;

  // SEARCH
  public searchInput = '';

  // TABLE
  public cohortsTableDataSource = new MatTableDataSource<ICohort>();
  public displayedColumns = ['select', 'cohortName', 'trainer', 'start'];
  public rowSelection = [];

  public isLoading = false;


  constructor(
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private cohortService: CohortService,
    private dialog: MatDialog
  ) { }


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
    });
  }


  // Action bar handlers
  public onDeleteActionClicked(): void {
    const dialogRef = this.dialog.open(DeleteCohortDialogComponent, {
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.isLoading = true;
        this.cohortService.deleteCohorts(this.dataTable.getSelectedRowsData()).subscribe(() => {
          this.performSearch();
        });
      }
    });
  }

  public onAddCohortButtonClicked(): void {
    console.warn('Add new cohort not implemented!');
  }

}
