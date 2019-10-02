import { Component, OnInit, ViewChild } from '@angular/core';
import { MatTableDataSource, MatDialog } from '@angular/material';
import { TechnologyCategoryModel } from 'projects/portal-core/src/app/_common/models/technology-category.model';
import { DataTableComponent } from 'projects/qa-common/src/app/data-table/data-table.component';
import { CourseModel } from 'projects/portal-core/src/app/_common/models/course.model';
import { TechnologyService } from '../_common/technology.service';
import { Router, ActivatedRoute } from '@angular/router';
import { IRowClickEvent } from 'projects/qa-common/src/app/data-table/models/row-click-event';

@Component({
  selector: 'app-technology-management',
  templateUrl: './technology-management.component.html'
})
export class TechnologyManagementComponent implements OnInit {
  @ViewChild('dataTable', { static: false }) dataTable: DataTableComponent<CourseModel>;

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
    });
  }


  public onAddCourseButtonClicked(): void {
    console.warn('Add new course not implemented!');

    // const dialogRef = this.dialog.open(NewCourseDialogComponent);
    // dialogRef.afterClosed().subscribe(result => {
    //   if (result) {
    //     // this.isLoading = true;
    //   }
    // });
  }

  onRowClicked(event: IRowClickEvent<CourseModel>): void {
    this.router.navigate(['qa', 'portal', 'admin', 'manage', 'technologies', event.data.id]);
  }

}
