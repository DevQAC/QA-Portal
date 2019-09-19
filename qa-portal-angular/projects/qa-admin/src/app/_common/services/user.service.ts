import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';

import { HttpHeaders, HttpClient } from '@angular/common/http';
import { GET_USERS, DEL_USER, ADD_USER, UPDATE_USER, TEST_USERS } from '../models/user.constant';
import { tap, catchError, delay } from 'rxjs/operators';
import { MessageService } from './message.service';
import { UserModel } from 'projects/portal-core/src/app/_common/models/user.model';
import { DUMMY_USERS } from './dummy-user-data';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
  };

  constructor(private http: HttpClient) { }

  public createUser(user: UserModel): Observable<UserModel> {
    return of(user).pipe(delay(Math.floor(Math.random() * 3500) + 500));
  }

  public searchUsers(search: string): Observable<UserModel[]> {
    console.warn('UserService.searchUsers is using dummy data!');
    return of(
      DUMMY_USERS
        .filter(c => c.userName.toLowerCase().includes(search.toLowerCase()) ||
          c.firstName.toLowerCase().includes(search.toLowerCase()))
    ).pipe(delay(Math.floor(Math.random() * 3500) + 500));
  }

  public deleteUsers(users: UserModel[]): Observable<void> {
    console.log('UserService.deleteUsers is not implemented! Input data:', users);
    return of(null).pipe(delay(Math.floor(Math.random() * 3500) + 500));
  }

  public updateUsersCohort(users: UserModel[], cohort: string): Observable<void> {
    console.log('UserService.updateUsersCohort is not implemented! Input data:', users, cohort);
    return of(null).pipe(delay(Math.floor(Math.random() * 3500) + 500));
  }

  public updateUsersRole(users: UserModel[], role: string): Observable<void> {
    console.log('UserService.updateUsersRole is not implemented! Input data:', users, role);
    return of(null).pipe(delay(Math.floor(Math.random() * 3500) + 500));
  }

}
