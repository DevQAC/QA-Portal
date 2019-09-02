import { Component, OnInit, ViewChild, ElementRef, Input } from '@angular/core';
import { IUser } from '../../_common/models/user.model';
import { ENTER, COMMA } from '@angular/cdk/keycodes';
import { FormControl } from '@angular/forms';
import { MatChipInputEvent, MatAutocomplete, MatAutocompleteSelectedEvent } from '@angular/material';

@Component({
  selector: 'app-user-management-console',
  templateUrl: './user-management-console.component.html',
  styleUrls: ['./user-management-console.component.css']
})
export class UserManagementConsoleComponent implements OnInit {
  displayedColumns: string[] = ['Username', 'Email', 'First Name', 'Last Name', 'Roles', 'Actions'];
  dataSource = USER_DATA;

  constructor() { }

  ngOnInit() {
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