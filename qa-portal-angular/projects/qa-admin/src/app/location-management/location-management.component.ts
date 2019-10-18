import { Component, OnInit, ViewChild } from '@angular/core';
import { LocationService } from '../_common/services/location.service';
import { MatDialog, MatTableDataSource } from '@angular/material';
import { QaErrorHandlerService } from 'projects/portal-core/src/app/_common/services/qa-error-handler.service';
import { LocationModel } from 'projects/portal-core/src/app/_common/models/location.model';
import { ActivatedRoute, Router } from '@angular/router';
import { DataTableComponent } from 'projects/qa-common/src/app/data-table/data-table.component';
import { IRowClickEvent } from 'projects/qa-common/src/app/data-table/models/row-click-event';
import { NewLocationDialogComponent } from './new-location-dialog/new-location-dialog.component';

@Component({
  selector: 'app-location-management',
  templateUrl: './location-management.component.html',
  styleUrls: ['./location-management.component.css']
})
export class LocationManagementComponent implements OnInit {

  @ViewChild('dataTable', { static: false }) dataTable: DataTableComponent<LocationModel>;

  // SEARCH
  public searchInput = '';

  // TABLE
  public locationsTableDataSource = new MatTableDataSource<LocationModel>();
  public displayedColumns = ['name'];
  public rowSelection = [];
  public isLoading = true;

  constructor(
    private locationService: LocationService,
    private dialog: MatDialog,
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private errorService: QaErrorHandlerService
  ) { }
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

    this.locationService.getAllLocations().subscribe(results => {
      this.locationsTableDataSource.data = results;
      this.isLoading = false;
    }, err => this.errorService.handleError(err));
  }

  public onAddLocationButtonClicked(): void {
    const dialogRef = this.dialog.open(NewLocationDialogComponent);
    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.performSearch();
      }
    });
  }

  onRowClicked(event: IRowClickEvent<LocationModel>): void {
    this.router.navigate(['qa', 'portal', 'admin', 'manage', 'locations', event.data.id]);
  }



}
