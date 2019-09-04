import { Component, OnInit } from '@angular/core';
import { IUser } from '../_common/models/user.model';
import { MatDialog } from '@angular/material';
import { AddUserDialogComponent } from '../add-user-dialog/add-user-dialog.component';
import { DelUserConfirmDialogComponent } from '../del-user-confirm-dialog/del-user-confirm-dialog.component';

@Component({
  selector: 'app-user-management-console',
  templateUrl: './user-management-console.component.html',
  styleUrls: ['./user-management-console.component.css']
})
export class UserManagementConsoleComponent implements OnInit {
  displayedColumns: string[] = ['Username', 'Email', 'First Name', 'Last Name', 'Roles', 'Actions'];
  dataSource = USER_DATA;

  constructor(public dialog: MatDialog) { }

  ngOnInit() {
  }

  editDialog(user: IUser): void {
    console.info(user);
    let dialogRef = this.dialog.open(AddUserDialogComponent, {
      width: '500px',
      data: user
    });
    // dialogRef.componentInstance.doSubmit.subscribe(() => {
    //   if (dialogRef.componentInstance.canSubmit === true) {
    //     this.onSubmit();
    //   }
    // });
    // dialogRef.afterClosed().subscribe(() => {
    // });

  }

  deleteConfirmDialog(user: IUser): void {
    let dialogRef = this.dialog.open(DelUserConfirmDialogComponent, {
      width: '500px',
      data: user
    });
  }


}

const USER_DATA: IUser[] = [
  { username: "joebloggs", email: "joe.bloggs@academytrainee.com", firstName: "Joe", lastName: "Bloggs", roles: ["cohort_CI_Intake_1"] },
  { username: "joebloggs", email: "joe.bloggs@academytrainee.com", firstName: "Joe", lastName: "Bloggs", roles: ["cohort_CI_Intake_1"] },
  { username: "joebloggs", email: "joe.bloggs@academytrainee.com", firstName: "Joe", lastName: "Bloggs", roles: ["cohort_CI_Intake_1"] },
  { username: "joebloggs", email: "joe.bloggs@academytrainee.com", firstName: "Joe", lastName: "Bloggs", roles: ["cohort_CI_Intake_1"] },
  { username: "joebloggs", email: "joe.bloggs@academytrainee.com", firstName: "Joe", lastName: "Bloggs", roles: ["cohort_CI_Intake_1"] },
  { username: "joebloggs", email: "joe.bloggs@academytrainee.com", firstName: "Joe", lastName: "Bloggs", roles: ["cohort_CI_Intake_1"] }
];