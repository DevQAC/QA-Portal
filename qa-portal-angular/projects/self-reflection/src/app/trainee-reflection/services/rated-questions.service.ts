import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { QuestionModel } from '../../_common/models/question.model';
import { GET_ALL_RATED_QUESTIONS_API } from '../../_common/models/trainee-reflection-constants';
import { HttpClient } from '@angular/common/http';
import {QUESTION_API} from "../../../../../portal-core/src/app/_common/models/portal-constants";

@Injectable()
export class RatedQuestionsService {

  constructor(private httpClient: HttpClient) { }
  getSelfReflectionQuestions(): Observable<QuestionModel[]> {
    return this.httpClient.get<QuestionModel[]>(QUESTION_API);
  }
}
