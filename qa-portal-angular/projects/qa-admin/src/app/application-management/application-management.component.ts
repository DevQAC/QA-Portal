import { Component, OnInit, ViewChild } from '@angular/core';
import { DataTableComponent } from 'projects/qa-common/src/app/data-table/data-table.component';
import { MatTableDataSource, MatDialog } from '@angular/material';
import { Router, ActivatedRoute } from '@angular/router';
import { IRowClickEvent } from 'projects/qa-common/src/app/data-table/models/row-click-event';
import { ApplicationService } from '../_common/services/application.service';
import { NewAppDialogComponent } from './new-app-dialog/new-app-dialog.component';
import { QaErrorHandlerService } from 'projects/portal-core/src/app/_common/services/qa-error-handler.service';
import { PortalApplicationModel } from 'projects/portal-core/src/app/_common/models/portal-application.model';

@Component({
  selector: 'app-application-management',
  templateUrl: './application-management.component.html',
  styleUrls: ['./application-management.component.css']
})
export class ApplicationManagementComponent implements OnInit {

  @ViewChild('dataTable', { static: false }) dataTable: DataTableComponent<PortalApplicationModel>;

  // SEARCH
  public searchInput = '';

  // TABLE
  public appsTableDataSource = new MatTableDataSource<PortalApplicationModel>();
  public displayedColumns = ['appName', 'baseUrl', 'displayOrder'];
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

    this.appService.getAllApplications().subscribe(results => {
      this.appsTableDataSource.data = results;
      this.isLoading = false;
    }, err => this.errorService.handleError(err));
  }

  public onAddAppButtonClicked(): void {
    this.dialog.open(NewAppDialogComponent, {}).afterClosed().subscribe(data => {
      if (data) {
        this.performSearch();
      }
    });
  }

  onRowClicked(event: IRowClickEvent<PortalApplicationModel>): void {
    this.router.navigate(['qa', 'portal', 'admin', 'manage', 'applications', event.data.id]);
  }

}
