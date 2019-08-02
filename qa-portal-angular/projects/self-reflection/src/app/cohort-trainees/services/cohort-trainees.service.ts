import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {QuestionModel} from '../../_common/models/question.model';
import {GET_ALL_RATED_QUESTIONS_API} from '../../_common/models/trainee-reflection-constants';
import {QaCohortModel} from '../models/qa-cohort.model';

@Injectable()
export class CohortTraineesService {

  constructor(private httpClient: HttpClient) {
  }

  getCohort(): Observable<QaCohortModel> {
    return this.httpClient.get<QaCohortModel>('/user-api/user/cohort');
  }


}
