import { Component, OnInit, ViewChild } from '@angular/core';
import { MatTableDataSource, MatDialog } from '@angular/material';
import { TechnologyCategoryModel } from 'projects/portal-core/src/app/_common/models/technology-category.model';
import { DataTableComponent } from 'projects/qa-common/src/app/data-table/data-table.component';
import { CourseModel } from 'projects/portal-core/src/app/_common/models/course.model';
import { TechnologyService } from '../_common/technology.service';
import { Router, ActivatedRoute } from '@angular/router';
import { IRowClickEvent } from 'projects/qa-common/src/app/data-table/models/row-click-event';
import { NewCategoryDialogComponent } from './new-category-dialog/new-category-dialog.component';
import { QaErrorHandlerService } from 'projects/portal-core/src/app/_common/services/qa-error-handler.service';

@Component({
  selector: 'app-technology-management',
  templateUrl: './technology-management.component.html'
})
export class TechnologyManagementComponent implements OnInit {
  @ViewChild('dataTable', { static: false }) dataTable: DataTableComponent<TechnologyCategoryModel>;

  // SEARCH
  public searchInput = '';

  // TABLE
  public categoriesTableDataSource = new MatTableDataSource<TechnologyCategoryModel>();
  public displayedColumns = ['categoryName', 'technologies'];
  public rowSelection = [];
  public isLoading = true;

  constructor(
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private techService: TechnologyService,
    private errorService: QaErrorHandlerService,
    private dialog: MatDialog) { }

  ngOnInit() {
    this.searchInput = this.activatedRoute.snapshot.queryParams.search || this.searchInput;
    this.performSearch();
  }

  public performSearch(): void {
    if (this.dataTable) {
      this.dataTable.deselectAllRows();
    }
    this.isLoading = true;
    this.router.navigate([], { relativeTo: this.activatedRoute, queryParams: { search: this.searchInput } });

    this.techService.getAllCategories().subscribe(results => {
      this.categoriesTableDataSource.data = results;
      this.isLoading = false;
    }, err => this.errorService.handleError(err));
  }


  public onAddCategoryButtonClicked(): void {

    const dialogRef = this.dialog.open(NewCategoryDialogComponent);
    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.performSearch();
        // this.isLoading = true;
      }
    });
  }

  onRowClicked(event: IRowClickEvent<CourseModel>): void {
    this.router.navigate(['qa', 'portal', 'admin', 'manage', 'technologies', event.data.id]);
  }

}
