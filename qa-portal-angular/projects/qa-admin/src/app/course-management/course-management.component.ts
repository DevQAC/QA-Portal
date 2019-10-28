import { Component, OnInit, ViewChild } from '@angular/core';
import { DataTableComponent } from 'projects/qa-common/src/app/data-table/data-table.component';
import { MatTableDataSource, MatDialog } from '@angular/material';
import { CourseModel } from 'projects/portal-core/src/app/_common/models/course.model';
import { Router, ActivatedRoute } from '@angular/router';
import { CourseService } from '../_common/services/course.service';
import { IRowClickEvent } from 'projects/qa-common/src/app/data-table/models/row-click-event';
import { NewCourseDialogComponent } from './new-course-dialog/new-course-dialog.component';
import { QaErrorHandlerService } from 'projects/portal-core/src/app/_common/services/qa-error-handler.service';

@Component({
  selector: 'app-course-management',
  templateUrl: './course-management.component.html'
})
export class CourseManagementComponent implements OnInit {

  @ViewChild('dataTable', { static: false }) dataTable: DataTableComponent<CourseModel>;

  // SEARCH
  public searchInput = '';

  // TABLE
  public coursesTableDataSource = new MatTableDataSource<CourseModel>();
  public displayedColumns = ['courseName', 'courseCode', 'duration'];
  public rowSelection = [];
  public isLoading = true;

  constructor(
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private courseService: CourseService,
    private errorService: QaErrorHandlerService,
    private dialog: MatDialog) {
  }

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

    this.courseService.getAllCourses().subscribe(results => {
      this.coursesTableDataSource.data = results;
      this.isLoading = false;
    }, err => this.errorService.handleError(err));
  }


  public onAddCourseButtonClicked(): void {

    const dialogRef = this.dialog.open(NewCourseDialogComponent);
    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.performSearch();
      }
    });
  }

  onRowClicked(event: IRowClickEvent<CourseModel>): void {
    this.router.navigate(['qa', 'portal', 'admin', 'manage', 'courses', event.data.id]);
  }

}
