import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {ICvModel} from '../../_common/models/qac-cv-db.model';
import {
  APPLICATION_PDF_CONTENT_TYPE, GENERATE_CV_URL,
  GET_CURRENT_CV_URL,
  GET_CV_FOR_ID_URL,
  GET_SKILLS_FOR_TRAINEE_URL,
  SAVE_CV_DATA_URL
} from '../../_common/models/cv.constants';
import {take} from 'rxjs/operators';

@Injectable()
export class ViewCvService {

  httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
  };

  constructor(private http: HttpClient) {
  }

  /** GET cv by id. Will 404 if id not found */
  getCvForId(id: string): Observable<ICvModel> {
    return this.http.get<ICvModel>(GET_CV_FOR_ID_URL + id).pipe(
      take(1)
    );
  }

  getSkillsForTrainee(): Observable<any> {
    return this.http.get<any>(GET_SKILLS_FOR_TRAINEE_URL).pipe(
      take(1)
    );
  }

  getCurrentCvForTrainee(): Observable<ICvModel> {
    return this.http.get<ICvModel>(GET_CURRENT_CV_URL).pipe(
      take(1)
    );
  }

  displayPdf(cv: ICvModel) {
    const httpOptions = {
      responseType: 'arraybuffer' as 'json'
    };
    return this.http.post<any>(GENERATE_CV_URL, cv, httpOptions).subscribe((response) => {
      const file = new Blob([response], {type: APPLICATION_PDF_CONTENT_TYPE});
      const fileURL = URL.createObjectURL(file);
      window.open(fileURL, '_blank');
    });
  }

  // /** POST: add a new cv to the server */
  createCv(cv: ICvModel): Observable<ICvModel> {
    return this.http.post<ICvModel>(SAVE_CV_DATA_URL, cv, this.httpOptions).pipe(
      take(1)
    );
  }

  /** PUT: update the cv on the server */
  updateCv(cv: ICvModel): Observable<ICvModel> {
    return this.http.put<ICvModel>(SAVE_CV_DATA_URL, cv, this.httpOptions).pipe(
      take(1)
    );
  }
}
