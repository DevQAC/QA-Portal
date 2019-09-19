import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog, MatTableDataSource } from '@angular/material';
import { UserService } from '../_common/services/user.service';
import { UserModel } from 'projects/portal-core/src/app/_common/models/user.model';
import { DataTableComponent } from 'projects/qa-common/src/app/data-table/data-table.component';
import { ActivatedRoute, Router } from '@angular/router';
import { DeleteUserDialogComponent } from './delete-user-dialog/delete-user-dialog.component';
import { UpdateUserCohortDialogComponent } from './update-user-cohort-dialog/update-user-cohort-dialog.component';
import { UpdateUserRoleDialogComponent } from './update-user-role-dialog/update-user-role-dialog.component';
import { AddUserDialogComponent } from './add-user-dialog/add-user-dialog.component';

@Component({
  selector: 'app-user-management',
  templateUrl: './user-management.component.html',
  styleUrls: ['./user-management.component.css']
})
export class UserManagementComponent implements OnInit {
  @ViewChild('table', { static: false }) dataTable: DataTableComponent<UserModel>;

  public dataSource = new MatTableDataSource<UserModel>();
  public displayedColumns = ['select', 'Username', 'First Name', 'Last Name'];

  public searchInput = '';

  public isLoading = false;

  constructor(
    public dialog: MatDialog,
    private userService: UserService,
    private activatedRoute: ActivatedRoute,
    private router: Router) { }


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

    this.userService.searchUsers(this.searchInput).subscribe(results => {
      this.dataSource.data = results;
      this.isLoading = false;
    });
  }


  public onDeleteActionClicked() {
    const users = this.dataTable.getSelectedRowsData();
    const dialogRef = this.dialog.open(DeleteUserDialogComponent, { data: users });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.isLoading = true;
        this.userService.deleteUsers(users).subscribe(() => {
          this.performSearch();
        });
      }
    });
  }

  public onUpdateCohortActionClicked() {
    const dialogRef = this.dialog.open(UpdateUserCohortDialogComponent, {});

    dialogRef.afterClosed().subscribe(cohort => {
      if (cohort) {
        this.isLoading = true;
        this.userService.updateUsersCohort(this.dataTable.getSelectedRowsData(), cohort).subscribe(() => {
          this.performSearch();
        });
      }
    });
  }

  public onUpdateRoleActionClicked() {
    const dialogRef = this.dialog.open(UpdateUserRoleDialogComponent, {});

    dialogRef.afterClosed().subscribe(role => {
      if (role) {
        this.isLoading = true;
        this.userService.updateUsersRole(this.dataTable.getSelectedRowsData(), role).subscribe(() => {
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
    });
  }

}
