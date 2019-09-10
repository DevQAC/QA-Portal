import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {ICvModel} from '../models/qac-cv-db.model';
import {map} from 'rxjs/operators';
import {MessageService} from './message.service';
import * as _ from 'lodash';
import {APPROVE_CV, FAIL_CV, GET_ALL_CVS, GET_CV_FOR_ID, GET_SKILLS_FOR_TRAINEE, SAVE_CV_DATA, SUBMIT_CV} from '../models/cv.constants';

@Injectable()
export class ViewCvService {

  httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
  };

  constructor(
    private http: HttpClient,
    private messageService: MessageService) {
  }

  /** GET cv by id. Will 404 if id not found */

  getCvForId(id: string): Observable<ICvModel> {
    return this.http.get<ICvModel>(GET_CV_FOR_ID + id);
  }

  getCurrentCvForTrainee(): Observable<ICvModel> {
    return this.getAllCvsForCurrentUser().pipe(
      map(allCvs => _.head(_.orderBy(allCvs, ['versionNumber'], ['desc'])))
    );
  }

  getSkillsForTrainee(): Observable<any> {
    return this.http.get<any>(GET_SKILLS_FOR_TRAINEE);
  }

  private getAllCvsForCurrentUser(): Observable<ICvModel[]> {
    return this.http.get<ICvModel[]>(GET_ALL_CVS, this.httpOptions);
  }

  getPDFService(cv: ICvModel) {
    const url = `cv-api/cv/generated`;
    const httpOptions = {
      'responseType': 'arraybuffer' as 'json'

    };
    return this.http.post<any>(url, cv, httpOptions);
  }

  //////// Save methods //////////

  // /** POST: add a new cv to the server */
  createCv(cv: ICvModel): Observable<ICvModel> {
    return this.http.post<ICvModel>(SAVE_CV_DATA, cv, this.httpOptions);
  }

  /** PUT: update the cv on the server */
  updateCv(cv: ICvModel): Observable<ICvModel> {
    return this.http.put<ICvModel>(SAVE_CV_DATA, cv, this.httpOptions);
  }

  submitCv(cv: ICvModel): Observable<ICvModel> {
    return this.http.put<ICvModel>(SUBMIT_CV, cv, this.httpOptions);
  }

  approveCv(cv: ICvModel): Observable<ICvModel> {
    return this.http.put<ICvModel>(APPROVE_CV, cv, this.httpOptions);
  }

  failCv(cv: ICvModel): Observable<ICvModel> {
    return this.http.put<ICvModel>(FAIL_CV, cv, this.httpOptions);
  }

  /** Log a ViewCvService message with the MessageService */
  private log(message: string) {
    this.messageService.add(`ViewCvService: ${message}`);
  }
}
