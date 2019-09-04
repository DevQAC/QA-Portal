import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { ICvModel } from '../models/qac-cv-db.model';
import { catchError, map, tap } from 'rxjs/operators';
import { MessageService } from './message.service';

import * as _ from 'lodash';
import { GET_ALL_CVS, POST_CV_DATA, GET_CURRENT_CV } from '../models/cv.constants';

@Injectable({ providedIn: 'root' })
export class ViewCvService {



  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(
    private http: HttpClient,
    private messageService: MessageService) { }


  //   return <ICvModel>
  //     {
  //       "status": "Passed",
  //       "id": 123,
  //       "userName": "xx42069xx",
  //       "firstName": "Ian",
  //       "surname": "Owen",
  //       "fullName": "Ian Owen",
  //       "cohort": "2",
  //       "profile": {
  //         "profileDetails": "test p_detail",
  //         "profileFeedback": [
  //           {
  //             "reviewer": "me",
  //             "date": "2019-01-03",
  //             "comment": "hmmmm"
  //           }
  //         ]
  //       },
  //       "allSkills": [
  //         {
  //           "programmingLanguages": [
  //             "Java",
  //             "Python",
  //             "HTML"
  //           ],
  //           "ides": [
  //             "VS Code",
  //             "Eclipse",
  //             "Notepad++"
  //           ],
  //           "operatingSystems": [
  //             "Windows",
  //             "Linux",
  //             "DOS",
  //             "CPM",
  //             "OS/2",
  //             "QNX"
  //           ],
  //           "devops": [
  //             "Jenkins",
  //             "Alfred",
  //             "Jeeves"
  //           ],
  //           "databases": [
  //             "Mongo",
  //             "MySQL",
  //             "MS Excel"
  //           ],
  //           "platforms": [
  //             "Concrete",
  //             "Glass",
  //             "Wood"
  //           ],
  //           "other": [
  //             "Heat Vision",
  //             "Super Speed",
  //             "I'm running out of ideas"
  //           ]
  //         }
  //       ],
  //       "allQualifications": [
  //         {
  //           "qualificationDetails": "qual test1",
  //           "qualificationFeedback": [
  //             {
  //               "reviewer": "me",
  //               "date": "2019-01-03",
  //               "comment": "everyone has that quali"
  //             }
  //           ]
  //         }
  //         ,
  //         {
  //           "qualificationDetails": "qual test2",
  //           "qualificationFeedback": [
  //             {
  //               "reviewer": "me2",
  //               "date": "2019-01-03",
  //               "comment": "everyone has that quali2"
  //             }
  //           ]
  //         }
  //       ],
  //       "allWorkExperience": [
  //         {
  //           "jobTitle": "hacker",
  //           "start": "2019-01-01",
  //           "end": "2019-01-03",
  //           "workExperienceDetails": "i hacked the pentagon ",
  //           "workExperienceFeedback": [
  //             {
  //               "reviewer": "me",
  //               "date": "2019-01-03",
  //               "comment": "good"
  //             }
  //           ]
  //         },
  //         {
  //           "jobTitle": "hacker2",
  //           "start": "2019-01-01",
  //           "end": "2019-01-03",
  //           "workExperienceDetails": "i hacked the pentagon ",
  //           "workExperienceFeedback": [
  //             {
  //               "reviewer": "me",
  //               "date": "2019-01-03",
  //               "comment": "good2"
  //             }
  //           ]
  //         }
  //       ],
  //       "hobbies": {
  //         "hobbiesDetails": "test h_detail",
  //         "hobbiesFeedback": [
  //           {
  //             "reviewer": "ne",
  //             "date": "2019-01-03",
  //             "comment": "noice"
  //           }
  //         ]
  //       }
  //     }
  // }



  // // }




  // /** GET cv by id. Return `undefined` when id not found */
  // getICvModelNo404<Data>(id: number): Observable<ICvModel> {
  //   const url = `${this.getUrl}/?id=${id}`;
  //   return this.http.get<ICvModel[]>(url)
  //     .pipe(
  //       map(cvs => cvs[0]), // returns a {0|1} element array
  //       tap(h => {
  //         const outcome = h ? `fetched` : `did not find`;
  //         this.log(`${outcome} cv id=${id}`);
  //       }),
  //       catchError(this.handleError<ICvModel>(`getICvModel id=${id}`))
  //     );
  // }

  /** GET cv by id. Will 404 if id not found */
  getLatestCvForCurrentUser(): Observable<ICvModel> {
    return this.getAllCvsForCurrentUser().pipe(
      map(allCvs => _.head(_.orderBy(allCvs, ['versionNumber'], ['desc'])))
    );
  }

  private getAllCvsForCurrentUser(): Observable<ICvModel[]> {
    return this.http.get<ICvModel[]>(GET_CURRENT_CV, this.httpOptions).pipe(
      catchError(this.handleError<ICvModel[]>(`getICvModel for current user`))
    );

  }

  //////// Save methods //////////

  // /** POST: add a new cv to the server */
  createCv(cv: ICvModel): Observable<ICvModel> {
    return this.http.post<ICvModel>(POST_CV_DATA, cv, this.httpOptions).pipe(
      tap((newICvModel: ICvModel) => this.log(`added cv w/ id=${newICvModel.id}`)),
      catchError(this.handleError<ICvModel>('addICvModel'))
    );
  }

  /** PUT: update the cv on the server */
  updateCv(cv: ICvModel): Observable<ICvModel> {
    return this.http.put(POST_CV_DATA, cv, this.httpOptions).pipe(
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