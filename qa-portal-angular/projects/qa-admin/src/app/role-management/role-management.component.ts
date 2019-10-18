import { Component, OnInit, ViewChild } from '@angular/core';
import { RoleService } from '../_common/services/role.service';
import { NewRoleDialogComponent } from './new-role-dialog/new-role-dialog.component';
import { DataTableComponent } from 'projects/qa-common/src/app/data-table/data-table.component';
import { MatTableDataSource, MatDialog } from '@angular/material';
import { ActivatedRoute, Router } from '@angular/router';
import { QaErrorHandlerService } from 'projects/portal-core/src/app/_common/services/qa-error-handler.service';
import { IRowClickEvent } from 'projects/qa-common/src/app/data-table/models/row-click-event';
import { RoleModel } from 'projects/portal-core/src/app/_common/models/role.model';

@Component({
  selector: 'app-role-management',
  templateUrl: './role-management.component.html'
})
export class RoleManagementComponent implements OnInit {

@ViewChild('dataTable', { static: false }) dataTable: DataTableComponent<RoleModel>;

  // SEARCH
  public searchInput = '';

  // TABLE
  public rolesTableDataSource = new MatTableDataSource<RoleModel>();
  public displayedColumns = ['name', 'application'];
  public rowSelection = [];
  public isLoading = true;

  constructor(
    private roleService: RoleService,
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

    this.roleService.getPortalRoles().subscribe(results => {
      this.rolesTableDataSource.data = results;
      this.isLoading = false;
    }, err => this.errorService.handleError(err));
  }

  public onAddRoleButtonClicked(): void {
    const dialogRef = this.dialog.open(NewRoleDialogComponent);
    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.performSearch();
      }
    });
  }

  onRowClicked(event: IRowClickEvent<RoleModel>): void {
    this.router.navigate(['qa', 'portal', 'admin', 'manage', 'roles', event.data.id]);
  }
}
