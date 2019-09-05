import { Injectable } from '@angular/core';
import { IUserModel } from '../models/user.model';
import { InMemoryDbService } from 'angular-in-memory-web-api';

@Injectable({
  providedIn: 'root'
})
export class InMemoryDataService implements InMemoryDbService {

  createDb() {
    const users: IUserModel[] = [
      { id: 1, username: "joebloggs", email: "joe.bloggs@academytrainee.com", firstName: "Joe", lastName: "Bloggs", roles: ["training-user"], cohorts: ["cohort_CI_Intake_1"] },
      { id: 2, username: "joebloggs", email: "joe.bloggs@academytrainee.com", firstName: "Joe", lastName: "Bloggs", roles: ["training-user"], cohorts: ["cohort_CI_Intake_1"] },
      { id: 3, username: "joebloggs", email: "joe.bloggs@academytrainee.com", firstName: "Joe", lastName: "Bloggs", roles: ["training-user"], cohorts: ["cohort_CI_Intake_1"] },
      { id: 4, username: "joebloggs", email: "joe.bloggs@academytrainee.com", firstName: "Joe", lastName: "Bloggs", roles: ["training-user"], cohorts: ["cohort_CI_Intake_1"] },
      { id: 5, username: "joebloggs", email: "joe.bloggs@academytrainee.com", firstName: "Joe", lastName: "Bloggs", roles: ["training-user"], cohorts: ["cohort_CI_Intake_1"] },
      { id: 6, username: "joebloggs", email: "joe.bloggs@academytrainee.com", firstName: "Joe", lastName: "Bloggs", roles: ["training-user"], cohorts: ["cohort_CI_Intake_1"] }
    ];

    return { users }


  }

  genId(users: IUserModel[]): number {
    return users.length > 0 ? Math.max(...users.map(user => user.id)) + 1 : 11;
  }
}
