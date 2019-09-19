import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {CREATE_USER_URL, DELETE_USER_URL, GET_ALL_USERS_URL, UPDATE_USER_URL} from '../models/user.constant';
import {UserModel} from '../../../../../portal-core/src/app/_common/models/user.model';
import {take} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'}),
  };

  constructor(
    private http: HttpClient) {
  }

  getAllUsers(): Observable<UserModel[]> {
    return this.http.get<UserModel[]>(GET_ALL_USERS_URL, this.httpOptions).pipe(
      take(1)
    );
  }

  deleteUserByUsername(id: number): Observable<UserModel> {
    return this.http.delete<UserModel>(`${DELETE_USER_URL}/${id}`).pipe(
      take(1)
    );
  }

  addUser(user: UserModel): Observable<UserModel> {
    return this.http.post<UserModel>(CREATE_USER_URL, user, this.httpOptions).pipe(
      take(1)
    );
  }

  updateUser(user: UserModel): Observable<UserModel> {
    return this.http.put<UserModel>(UPDATE_USER_URL, user, this.httpOptions).pipe(
      take(1)
    );
  }
}
