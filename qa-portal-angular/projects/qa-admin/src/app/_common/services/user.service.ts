import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { CREATE_USER_URL, DELETE_USERS_URL, GET_ALL_USERS_URL, UPDATE_USERS_URL, GET_USER_BY_USERNAME_URL, UPDATE_USER_URL } from '../models/user.constant';
import { take } from 'rxjs/operators';
import { UserDetailsModel } from '../../../../../portal-core/src/app/_common/models/user-details.model';
import { UserModel } from '../../../../../portal-core/src/app/_common/models/user.model';
import { O_DIRECT } from 'constants';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
  };

  constructor(
    private http: HttpClient) {
  }

  getAllUsers(): Observable<UserDetailsModel[]> {
    return this.http.get<UserDetailsModel[]>(GET_ALL_USERS_URL, this.httpOptions).pipe(
      take(1)
    );
  }

  deleteUsers(users: UserDetailsModel[]): Observable<any> {
    return this.http.put<any>(DELETE_USERS_URL, users).pipe(
      take(1)
    );
  }

  addUser(user: UserModel): Observable<UserModel> {
    const userDetails = new UserDetailsModel();
    user.email = user.userName;
    userDetails.user = user;
    userDetails.roleName = user.role;
    return this.http.post<UserModel>(CREATE_USER_URL, userDetails, this.httpOptions).pipe(
      take(1)
    );
  }

  updateUser(user: UserDetailsModel): Observable<UserDetailsModel> {
    return this.http.put<UserDetailsModel>(UPDATE_USER_URL, user, this.httpOptions).pipe(
      take(1)
    );
  }

  updateRoleForUsers(users: UserDetailsModel[], role: string) {
    users.forEach(u => u.roleName = role);
    return this.http.put<UserDetailsModel[]>(UPDATE_USERS_URL, users, this.httpOptions).pipe(
      take(1)
    );
  }

  updateCohortForUsers(users: UserDetailsModel[], cohortName: string) {
    users.forEach(u => u.cohortName = cohortName);
    return this.http.put<UserDetailsModel[]>(UPDATE_USERS_URL, users, this.httpOptions).pipe(
      take(1)
    );
  }

  getUserByUsername(username: string): Observable<UserDetailsModel> {
    return this.http.get<UserDetailsModel>(GET_USER_BY_USERNAME_URL + username, this.httpOptions).pipe(take(1));
  }
}
