import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {QuestionModel} from '../../_common/models/question.model';
import {FORM_TYPE_PLACEHOLDER, QUESTION_API} from '../../../../../portal-core/src/app/_common/models/portal-constants';

@Injectable()
export class ReflectionQuestionService {

  constructor(private httpClient: HttpClient) {
  }

  public getQuestionsByFormType(formType: string): Observable<QuestionModel[]> {
    return this.httpClient.get<QuestionModel[]>(QUESTION_API.replace(FORM_TYPE_PLACEHOLDER, formType));
  }
}
