import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {TraineeEvaluationSummaryModel} from '../models/trainee-evaluation-summary.model';
import {Observable} from 'rxjs';
import {GET_TRAINEE_EVALUATION_SUMMARY_URL} from '../../_common/models/course-feedback.constants';

@Injectable()
export class TraineeEvaluationSummaryService {

  constructor(private httpClient: HttpClient) {}

  public getTraineeEvaluationSummary(): Observable<TraineeEvaluationSummaryModel> {
    return this.httpClient.get<TraineeEvaluationSummaryModel>(GET_TRAINEE_EVALUATION_SUMMARY_URL);
  }
}
