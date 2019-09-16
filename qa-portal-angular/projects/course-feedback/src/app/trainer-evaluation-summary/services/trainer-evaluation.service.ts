import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {GET_TRAINER_EVALUATION_SUMMARY} from '../../_common/models/course-feedback.constants';
import {TrainerEvaluationSummaryModel} from '../models/trainer-evaluation-summary.model';

@Injectable()
export class TrainerEvaluationService {

  constructor(private httpClient: HttpClient) {
  }

  public getTrainerCourseEvaluationSummary(courseId): Observable<TrainerEvaluationSummaryModel> {
    return this.httpClient.get<TrainerEvaluationSummaryModel>(GET_TRAINER_EVALUATION_SUMMARY + courseId);
  }
}
