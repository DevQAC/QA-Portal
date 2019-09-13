import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { IUserModel } from '../models/user.model';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { GET_USERS, DEL_USER, ADD_USER, UPDATE_USER, TEST_USERS } from '../models/user.constant';
import { tap, catchError } from 'rxjs/operators';
import { MessageService } from './message.service';

@Injectable({
  providedIn: 'root'
})
export class UserService {


  /* defines the urls for api requests*/
  private get: string = TEST_USERS;
  private delete: string = TEST_USERS;
  private post: string = TEST_USERS;
  private put: string = TEST_USERS;



  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
  };

  constructor(private http: HttpClient,
    private messageService: MessageService) { }

  getAllUsers(): Observable<IUserModel[]> {
    return this.http.get<IUserModel[]>(this.get, this.httpOptions).pipe(
      catchError(this.handleError<IUserModel[]>(`retreving all registered users`)))
  }

  deleteUserByUsername(id: number): Observable<IUserModel[]> {
    return this.http.delete<IUserModel[]>(`${this.delete}/${id}`).pipe(
      catchError(this.handleError<IUserModel[]>(`Deleting selected user`))
    )
  }

  addUser(user: IUserModel): Observable<IUserModel[]> {
    return this.http.post<IUserModel[]>(this.post, user, this.httpOptions).pipe(
      catchError(this.handleError<IUserModel[]>(`Adding a New user`))
    )
  }

  updateUser(user: IUserModel): Observable<IUserModel[]> {
    return this.http.put<IUserModel[]>(this.put, user, this.httpOptions).pipe(
      tap(_ => this.log(`updated username=${user.username}`)),
      catchError(this.handleError<any>(`Updating Selected User`)))
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
