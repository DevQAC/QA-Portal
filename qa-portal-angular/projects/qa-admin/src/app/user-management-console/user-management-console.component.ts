import { Component, OnInit } from '@angular/core';
import { IUserModel } from '../_common/models/user.model';
import { MatDialog } from '@angular/material';
import { AddUserDialogComponent } from '../add-user-dialog/add-user-dialog.component';
import { DelUserConfirmDialogComponent } from '../del-user-confirm-dialog/del-user-confirm-dialog.component';
import { EditUserDialogComponent } from '../edit-user-dialog/edit-user-dialog.component';
import { UserService } from '../_common/services/user.service';

@Component({
  selector: 'app-user-management-console',
  templateUrl: './user-management-console.component.html',
  styleUrls: ['./user-management-console.component.css']
})
export class UserManagementConsoleComponent implements OnInit {
  displayedColumns: string[] = ['Username', 'Email', 'First Name', 'Last Name', 'Roles', 'Cohort', 'Actions'];
  public dataSource: any;

  private modalConfig = {
    maxWidth: '1000px'
  };

  constructor(public dialog: MatDialog, private service: UserService) { }

  ngOnInit() {
    this.dataSource = this.service.getAllUsers();
  }

  editDialog(user: IUserModel): void {
    console.info(user);
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

    let dialogRef = this.dialog.open(AddUserDialogComponent, this.modalConfig);
    dialogRef.componentInstance.dataChanged.subscribe(() => {
      if (dialogRef.componentInstance.canSubmit) {
        this.service.addUser(dialogRef.componentInstance.data);
      }
    });
    dialogRef.afterClosed().subscribe(() => {
    });
  }

  deleteConfirmDialog(user: IUserModel): void {
    let dialogRef = this.dialog.open(DelUserConfirmDialogComponent, {
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
