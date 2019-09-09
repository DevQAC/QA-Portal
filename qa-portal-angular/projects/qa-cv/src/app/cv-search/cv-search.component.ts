import { Component, OnDestroy, OnInit, ViewChild, AfterViewInit } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { CVSearchHistoryService } from './services/cv-search.service';
import { CVSearchFilterService } from './services/cv-search-filter.service';
import { CVSearchModel } from './models/cv-search-model';
import { Subscription } from 'rxjs';
import { QaErrorHandlerService } from '../../../../portal-core/src/app/_common/services/qa-error-handler.service';
import { FormControl } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { FilterModel } from './models/filter-search-model';

@Component({
  selector: 'app-cv-search',
  templateUrl: './cv-search.component.html'
})
export class CvSearchComponent implements OnInit {

  // Results table data
  public searchResultsDataSource = new MatTableDataSource<CVSearchModel>();
  public displayedColumns: string[] = ['name', 'cohort', 'status', 'clients'];

  // Filter options
  public filterOptions: FilterModel;

  // User input
  public inputName: string;
  public selectedCohort: string;
  public selectedTech: string;
  public selectedStatus: string;

  // Misc
  public isLoading = true;


  constructor(
    private cvSearchFilterService: CVSearchFilterService,
    private cvSearchHistoryService: CVSearchHistoryService,
    private router: Router,
    private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {
    const { name, cohort, tech, status } = this.activatedRoute.snapshot.queryParams;

    // populate search field sources with params (if found)
    this.inputName = name || '';
    this.selectedCohort = cohort || null;
    this.selectedTech = tech || null;
    this.selectedStatus = status || null;

    this.cvSearchFilterService.getFilters().subscribe(response => this.filterOptions = response); // Populate filters
    this.performSearch(); // search on page load
  }


  performSearch(): void {
    const name = this.inputName;
    const cohort = this.selectedCohort;
    const tech = this.selectedTech;
    const status = this.selectedStatus;
    this.isLoading = true;
    this.searchResultsDataSource.data = [];

    // Update URL params
    this.router.navigate([], { relativeTo: this.activatedRoute, queryParams: { name, cohort, tech, status } });
    this.cvSearchHistoryService.searchCVs(name, cohort, tech, status).subscribe(response => {
      this.searchResultsDataSource.data = response;
      this.isLoading = false;
    });
  }

  onResultRowClicked(row: CVSearchModel): void {
    this.router.navigate(['qa', 'portal', 'cv', 'admin', 'view', row.id]);
  }

}
