import { Component, OnInit, OnChanges, ViewChild } from '@angular/core';
import { MatTableDataSource, MatCheckboxChange, MatTable } from '@angular/material';
import { ICohort } from '../_common/models/cohort.model';
import { ActivatedRoute, Router } from '@angular/router';
import { CohortService } from '../_common/services/cohort.service';

@Component({
  selector: 'app-cohort-management',
  templateUrl: './cohort-management.component.html'
})
export class CohortManagementComponent implements OnInit {

  @ViewChild('table', {static: false}) matTable: MatTable<ICohort>;

  // SEARCH
  public searchInput = '';

  // TABLE
  public cohortsTableDataSource = new MatTableDataSource<ICohort>();
  public displayedColumns = ['select', 'cohortName', 'trainer', 'start'];
  public rowSelected = [];
  public allRowsSelected = false;
  public atLeastOneRowSelected = false;

  public isLoading = false;


  constructor(
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private cohortService: CohortService
  ) { }


  ngOnInit() {
    this.searchInput = this.activatedRoute.snapshot.queryParams.search || this.searchInput;
    this.performSearch();
  }

  public performSearch() {
    this.isLoading = true;
    this.router.navigate([], { relativeTo: this.activatedRoute, queryParams: { search: this.searchInput } });

    this.cohortService.searchCohorts(this.searchInput).subscribe(results => {
      this.cohortsTableDataSource.data = results;
      this.isLoading = false;
    });
  }

}
