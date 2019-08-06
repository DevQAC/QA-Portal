import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {QaCohortModel} from '../models/qa-cohort.model';
import {
  USER_API_COHORTS,
  USER_API_TRAINEES,
  USER_API_TRAINEES_FOR_REVIEW
} from 'projects/portal-core/src/app/_common/models/portal-constants';
import {GET_ALL_RATED_QUESTIONS_API} from '../../_common/models/trainee-reflection-constants';
import {TraineeModel} from '../models/trainee.model';
import {QuestionModel} from '../../_common/models/question.model';

@Injectable()
export class CohortTraineesService {

  constructor(private httpClient: HttpClient) {
  }

  getCohort(): Observable<QaCohortModel> {
    return this.httpClient.get<QaCohortModel>('/user-api/user/cohort');
  }

  getCohorts(): Observable<QaCohortModel[]> {
    return this.httpClient.get<QaCohortModel[]>(`${USER_API_COHORTS}`);
  }

  getTrainees(id: number): Observable<TraineeModel[]> {
    return this.httpClient.get<TraineeModel[]>(`${USER_API_TRAINEES}${id}`);
  }

  getTraineesForReview(cohortId: number): Observable<TraineeModel[]> {
    return this.httpClient.get<TraineeModel[]>(`${USER_API_TRAINEES_FOR_REVIEW}${cohortId}`);
  }
}
