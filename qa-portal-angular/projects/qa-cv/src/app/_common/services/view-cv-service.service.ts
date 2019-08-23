import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ICvModel } from '../models/qac-cv-db.model';
import { catchError, map, tap } from 'rxjs/operators';
import { MessageService } from './message.service';

@Injectable({ providedIn: 'root' })
export class ViewCvService {

  private getUrl = 'api/cvs';  // URL to web api

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(
    private http: HttpClient,
    private messageService: MessageService) { }

  /** GET cvs from the server */
  getAllCvs(): Observable<ICvModel[]> {
    return this.http.get<ICvModel[]>(this.getUrl)
      .pipe(
        tap(_ => this.log('fetched cvs')),
        catchError(this.handleError<ICvModel[]>('getICvModeles', []))
      );
  }

  /** GET cv by id. Return `undefined` when id not found */
  getICvModelNo404<Data>(id: number): Observable<ICvModel> {
    const url = `${this.getUrl}/?id=${id}`;
    return this.http.get<ICvModel[]>(url)
      .pipe(
        map(cvs => cvs[0]), // returns a {0|1} element array
        tap(h => {
          const outcome = h ? `fetched` : `did not find`;
          this.log(`${outcome} cv id=${id}`);
        }),
        catchError(this.handleError<ICvModel>(`getICvModel id=${id}`))
      );
  }

  /** GET cv by id. Will 404 if id not found */
  getCvById(id: number): Observable<ICvModel> {
    const url = `${this.getUrl}/${id}`;
    return this.http.get<ICvModel>(url).pipe(
      tap(_ => this.log(`fetched cv id=${id}`)),
      catchError(this.handleError<ICvModel>(`getICvModel id=${id}`))
    );
  }

  //////// Save methods //////////

  /** POST: add a new cv to the server */
  addCv(cv: ICvModel): Observable<ICvModel> {
    return this.http.post<ICvModel>(this.getUrl, cv, this.httpOptions).pipe(
      tap((newICvModel: ICvModel) => this.log(`added cv w/ id=${newICvModel.id}`)),
      catchError(this.handleError<ICvModel>('addICvModel'))
    );
  }

  /** PUT: update the cv on the server */
  updateCv(cv: ICvModel): Observable<any> {
    return this.http.put(this.getUrl, cv, this.httpOptions).pipe(
      tap(_ => this.log(`updated cv id=${cv.id}`)),
      catchError(this.handleError<any>('updateICvModel'))
    );
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