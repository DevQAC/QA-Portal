import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { RatedQuestionComponent } from './../../../../../qa-common/src/app/rated-question/rated-question.component';
import { Observable } from 'rxjs';
import { SELF_REFLECTION_API } from '../../../../../portal-core/src/app/_common/models/portal-constants';

@Injectable({
  providedIn: 'root'
})
export class QuestionsServiceService {
  cohortId = '1';
  constructor(private http:HttpClient) { }

  public getQuestions():Observable<any[]> {
    return this.http.get<any[]>(`${SELF_REFLECTION_API}/cohort/${this.cohortId}`);
  }
}
