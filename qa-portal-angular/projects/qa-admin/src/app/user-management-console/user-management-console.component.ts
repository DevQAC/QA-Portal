import {Component, OnInit} from '@angular/core';
import {IUserModel} from '../_common/models/user.model';
import {MatDialog, MatTableDataSource} from '@angular/material';
import {AddUserDialogComponent} from '../add-user-dialog/add-user-dialog.component';
import {DelUserConfirmDialogComponent} from '../del-user-confirm-dialog/del-user-confirm-dialog.component';
import {EditUserDialogComponent} from '../edit-user-dialog/edit-user-dialog.component';
import {UserService} from '../_common/services/user.service';
import {UserModel} from '../../../../portal-core/src/app/_common/models/user.model';

@Component({
  selector: 'app-user-management-console',
  templateUrl: './user-management-console.component.html',
  styleUrls: ['./user-management-console.component.css']
})
export class UserManagementConsoleComponent implements OnInit {
  displayedColumns: string[] = ['Username', 'Email', 'First Name', 'Last Name', 'Actions'];

  users: UserModel[];

  public dataSource: MatTableDataSource<UserModel>;

  private modalConfig = {
    maxWidth: '1000px'
  };

  constructor(public dialog: MatDialog,
              private service: UserService) {
  }

  ngOnInit() {
    this.service.getAllUsers().subscribe((response) => {
      this.users = response;
      this.dataSource = new MatTableDataSource(this.users);
    });
  }

  editDialog(user: UserModel): void {
    const dialogRef = this.dialog.open(EditUserDialogComponent, {
      ...this.modalConfig,
      data: user
    });
    dialogRef.componentInstance.dataChanged.subscribe(() => {
      if (dialogRef.componentInstance.data !== user && dialogRef.componentInstance.canSubmit) {
        this.service.updateUser(dialogRef.componentInstance.data);
      }
    });
    dialogRef.afterClosed().subscribe(() => {
    });
  }

  addDialog(): void {
    const dialogRef = this.dialog.open(AddUserDialogComponent, this.modalConfig);
    dialogRef.componentInstance.dataChanged.subscribe(() => {
      if (dialogRef.componentInstance.canSubmit) {
        this.service.addUser(dialogRef.componentInstance.data);
      }
    });
    dialogRef.afterClosed().subscribe(() => {
    });
  }

  deleteConfirmDialog(user: IUserModel): void {
    const dialogRef = this.dialog.open(DelUserConfirmDialogComponent, {
      ...this.modalConfig,
      data: user
    });

    dialogRef.componentInstance.dataChanged.subscribe(() => {
      if (dialogRef.componentInstance.canSubmit) {
        this.service.deleteUserByUsername(dialogRef.componentInstance.data.id);
      }
    });
    dialogRef.afterClosed().subscribe(() => {
    });
  }
}
