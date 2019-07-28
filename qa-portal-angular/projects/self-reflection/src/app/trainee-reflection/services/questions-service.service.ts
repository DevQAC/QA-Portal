import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { SELF_REFLECTION_QUESTION_API } from '../../../../../portal-core/src/app/_common/models/portal-constants';

@Injectable({
  providedIn: 'root'
})
export class QuestionsServiceService {
  cohortId = '1';
  constructor(private http: HttpClient) { }

  public getQuestions(): Observable<any[]> {
    return this.http.get<any[]>(`${SELF_REFLECTION_QUESTION_API}/cohort/${this.cohortId}`);
  }
}
