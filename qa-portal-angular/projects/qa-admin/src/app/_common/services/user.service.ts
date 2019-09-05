import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { IUserModel } from '../models/user.model';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { GET_USERS, DEL_USER, ADD_USER, UPDATE_USER } from '../models/User.constant';
import { tap, catchError } from 'rxjs/operators';
import { MessageService } from './message.service';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
  };

  constructor(private http: HttpClient,
    private messageService: MessageService) { }

  getAllUsers(): Observable<IUserModel[]> {
    return this.http.get<IUserModel[]>(GET_USERS, this.httpOptions).pipe(
      catchError(this.handleError<IUserModel[]>(`getICvModel for current user`)))
  }

  deleteUserByUsername(username: string): Observable<IUserModel[]> {
    debugger;
    return this.http.delete<IUserModel[]>(DEL_USER + username, this.httpOptions)
  }

  addUser(user: IUserModel): Observable<IUserModel[]> {
    return this.http.post<IUserModel[]>(ADD_USER, user, this.httpOptions)
  }

  updateUser(user: IUserModel): Observable<IUserModel[]> {
    return this.http.put<IUserModel[]>(UPDATE_USER, user, this.httpOptions).pipe(
      tap(_ => this.log(`updated username=${user.username}`)),
      catchError(this.handleError<any>('updateIUserModel')))
  }


  /**
     * Handle Http operation that failed.
     * Let the app continue.
     * @param operation - name of the operation that failed
     * @param result - optional value to return as the observable result
     */
  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead

      // TODO: better job of transforming error for user consumption
      this.log(`${operation} failed: ${error.message}`);

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }

  /** Log a ViewCvService message with the MessageService */
  private log(message: string) {
    this.messageService.add(`ViewCvService: ${message}`);
  }
}
