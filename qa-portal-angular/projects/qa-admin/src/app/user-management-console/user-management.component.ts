import {Component, OnInit, ViewChild} from '@angular/core';
import {MatDialog, MatTableDataSource} from '@angular/material';
import {UserService} from '../_common/services/user.service';
import {DataTableComponent} from 'projects/qa-common/src/app/data-table/data-table.component';
import {ActivatedRoute, Router} from '@angular/router';
import {DeleteUserDialogComponent} from './delete-user-dialog/delete-user-dialog.component';
import {UpdateUserCohortDialogComponent} from './update-user-cohort-dialog/update-user-cohort-dialog.component';
import {UpdateUserRoleDialogComponent} from './update-user-role-dialog/update-user-role-dialog.component';
import {AddUserDialogComponent} from './add-user-dialog/add-user-dialog.component';
import {UserDetailsModel} from '../../../../portal-core/src/app/_common/models/user-details.model';
import { IRowClickEvent } from 'projects/qa-common/src/app/data-table/models/row-click-event';
import { QaErrorHandlerService } from 'projects/portal-core/src/app/_common/services/qa-error-handler.service';

@Component({
  selector: 'app-user-management',
  templateUrl: './user-management.component.html',
  styleUrls: ['./user-management.component.css']
})
export class UserManagementComponent implements OnInit {
  @ViewChild('table', {static: false}) dataTable: DataTableComponent<UserDetailsModel>;

  public dataSource = new MatTableDataSource<UserDetailsModel>();

  public displayedColumns = ['select', 'Username', 'First Name', 'Last Name'];

  public searchInput = '';

  public isLoading = false;

  constructor(
    public dialog: MatDialog,
    private userService: UserService,
    private activatedRoute: ActivatedRoute,
    private errorService: QaErrorHandlerService,
    private router: Router) {
  }


  ngOnInit() {
    this.searchInput = this.activatedRoute.snapshot.queryParams.search || this.searchInput;
    this.performSearch();
  }

  public performSearch() {
    if (!!this.dataTable) { // Check if dataTable is defined. This isn't ready on first search (not that it matters)
      this.dataTable.deselectAllRows();
    }
    this.isLoading = true;
    this.router.navigate([], {relativeTo: this.activatedRoute, queryParams: {search: this.searchInput}});

    this.userService.getAllUsers().subscribe(results => {
      // TODO - Add search filter
      this.dataSource.data = results;
      this.isLoading = false;
    });
  }

  public onDeleteActionClicked() {
    const users = this.dataTable.getSelectedRowsData();
    const dialogRef = this.dialog.open(DeleteUserDialogComponent, {data: users});
    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.isLoading = true;
        this.userService.deleteUsers(users).subscribe(() => {
          this.performSearch();
        });
      }
    });
  }

  onAddUserClicked(): void {
    const dialogRef = this.dialog.open(AddUserDialogComponent, {});
    dialogRef.afterClosed().subscribe(user => {
      if (user) {
        this.performSearch();
      }
    }, err => this.errorService.handleError(err));
  }

  public onRowClicked(event: IRowClickEvent<UserDetailsModel>): void {
    this.router.navigate(['qa', 'portal', 'admin', 'manage', 'users', event.data.user.userName]);
  }
}
