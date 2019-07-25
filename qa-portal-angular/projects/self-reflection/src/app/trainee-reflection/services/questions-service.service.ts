import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { RatedQuestionComponent } from './../../../../../qa-common/src/app/rated-question/rated-question.component';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class QuestionsServiceService {
  questionsUrl: string = 'localhost:8081/getQuestionsForCohort'
  cohortId = '15';
  constructor(private http:HttpClient) { }

  getQuestions():Observable<RatedQuestionComponent[]> {
    return this.http.get<RatedQuestionComponent[]>('${this.questionsUrl}${this.cohortId}');
  }
}
