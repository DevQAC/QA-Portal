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
  displayedColumns: string[] = ['Username', 'Email', 'First Name', 'Last Name', 'Roles', 'Actions'];
  dataSource = USER_DATA;

  constructor(public dialog: MatDialog, private service: UserService) { }

  ngOnInit() {
  }

  editDialog(user: IUserModel): void {
    console.info(user);
    let dialogRef = this.dialog.open(EditUserDialogComponent, {
      width: '500px',
      data: user
    });
    dialogRef.componentInstance.dataChanged.subscribe(() => {
      if (dialogRef.componentInstance.data !== user && dialogRef.componentInstance.canSubmit) {
        this.service.updateUser(dialogRef.componentInstance.data)
      }
    });
    dialogRef.afterClosed().subscribe(() => {
    });
  }

  addDialog(): void {

    let dialogRef = this.dialog.open(AddUserDialogComponent, {
      width: '500px',
    });
    dialogRef.componentInstance.dataChanged.subscribe(() => {
      if (dialogRef.componentInstance.canSubmit) {
        this.service.addUser(dialogRef.componentInstance.data)
      }
    });
    dialogRef.afterClosed().subscribe(() => {
    });
  }

  deleteConfirmDialog(user: IUserModel): void {
    let dialogRef = this.dialog.open(DelUserConfirmDialogComponent, {
      width: '500px',
      data: user
    });

    dialogRef.componentInstance.dataChanged.subscribe(() => {
      if (dialogRef.componentInstance.canSubmit) {
        debugger;
        this.service.deleteUserByUsername(dialogRef.componentInstance.data.username)
      }
    });
    dialogRef.afterClosed().subscribe(() => {
    });
  }


}

const USER_DATA: IUserModel[] = [
  { username: "joebloggs", email: "joe.bloggs@academytrainee.com", firstName: "Joe", lastName: "Bloggs", roles: ["cohort_CI_Intake_1"] },
  { username: "joebloggs", email: "joe.bloggs@academytrainee.com", firstName: "Joe", lastName: "Bloggs", roles: ["cohort_CI_Intake_1"] },
  { username: "joebloggs", email: "joe.bloggs@academytrainee.com", firstName: "Joe", lastName: "Bloggs", roles: ["cohort_CI_Intake_1"] },
  { username: "joebloggs", email: "joe.bloggs@academytrainee.com", firstName: "Joe", lastName: "Bloggs", roles: ["cohort_CI_Intake_1"] },
  { username: "joebloggs", email: "joe.bloggs@academytrainee.com", firstName: "Joe", lastName: "Bloggs", roles: ["cohort_CI_Intake_1"] },
  { username: "joebloggs", email: "joe.bloggs@academytrainee.com", firstName: "Joe", lastName: "Bloggs", roles: ["cohort_CI_Intake_1"] }
];