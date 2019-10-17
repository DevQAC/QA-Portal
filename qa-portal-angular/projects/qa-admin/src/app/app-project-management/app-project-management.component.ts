import { Component, OnInit, ViewChild } from '@angular/core';
import { PortalProjectModel } from 'projects/portal-core/src/app/_common/models/portal-project.model';
import { DataTableComponent } from 'projects/qa-common/src/app/data-table/data-table.component';
import { MatTableDataSource, MatDialog } from '@angular/material';
import { Router, ActivatedRoute } from '@angular/router';
import { QaErrorHandlerService } from 'projects/portal-core/src/app/_common/services/qa-error-handler.service';
import { IRowClickEvent } from 'projects/qa-common/src/app/data-table/models/row-click-event';
import { ApplicationService } from '../_common/services/application.service';
import { NewProjectDialogComponent } from './new-project-dialog/new-project-dialog.component';

@Component({
  selector: 'app-app-project-management',
  templateUrl: './app-project-management.component.html',
  styleUrls: ['./app-project-management.component.css']
})
export class AppProjectManagementComponent implements OnInit {


  @ViewChild('dataTable', { static: false }) dataTable: DataTableComponent<PortalProjectModel>;

  // SEARCH
  public searchInput = '';

  // TABLE
  public projectsTableDataSource = new MatTableDataSource<PortalProjectModel>();
  public displayedColumns = ['projectName', 'pages'];
  public rowSelection = [];
  public isLoading = true;

  constructor(
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private appService: ApplicationService,
    private errorService: QaErrorHandlerService,
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

    this.appService.getAllProjects().subscribe(
      results => {
        this.projectsTableDataSource.data = results;
        this.isLoading = false;
      }, err => this.errorService.handleError(err));
  }

  public onAddProjectButtonClicked(): void {
    this.dialog.open(NewProjectDialogComponent, {}).afterClosed().subscribe(data => {
      if (data) {
        this.performSearch();
      }
    });
  }

  onRowClicked(event: IRowClickEvent<PortalProjectModel>): void {
    this.router.navigate(['qa', 'portal', 'admin', 'manage', 'app-projects', event.data.id]);
  }
}
