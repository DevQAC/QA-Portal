import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {ICvModel} from '../models/qac-cv-db.model';
import {MessageService} from './message.service';
import {GET_CURRENT_CV, GET_CV_FOR_ID, GET_SKILLS_FOR_TRAINEE, SAVE_CV_DATA} from '../models/cv.constants';
import {take} from 'rxjs/operators';

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

  getSkillsForTrainee(): Observable<any> {
    return this.http.get<any>(GET_SKILLS_FOR_TRAINEE);
  }

  getCurrentCvForCurrentUser(): Observable<ICvModel> {
    return this.http.get<ICvModel>(GET_CURRENT_CV);
  }

  getPDFService(cv: ICvModel) {
    const url = `cv-api/cv/generated`;
    const httpOptions = {
      'responseType': 'arraybuffer' as 'json'

    };
    return this.http.post<any>(url, cv, httpOptions);
  }

  // /** POST: add a new cv to the server */
  createCv(cv: ICvModel): Observable<ICvModel> {
    return this.http.post<ICvModel>(SAVE_CV_DATA, cv, this.httpOptions).pipe(
      take(1)
    );
  }

  /** PUT: update the cv on the server */
  updateCv(cv: ICvModel): Observable<ICvModel> {
    return this.http.put<ICvModel>(SAVE_CV_DATA, cv, this.httpOptions).pipe(
      take(1)
    );
  }

  /** Log a ViewCvService message with the MessageService */
  private log(message: string) {
    this.messageService.add(`ViewCvService: ${message}`);
  }
}
