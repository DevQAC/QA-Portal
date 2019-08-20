import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {
  CREATE_EVALUATION_FORM_URL,
  GET_CURRENT_EVALUATION_FORM_FOR_TRAINEE_URL,
  UPDATE_EVALUATION_FORM_URL
} from '../models/course-feedback.constants';
import {EvaluationFormModel} from '../models/evaluation-form.model';

@Injectable()
export class EvaluationService {

  constructor(private httpClient: HttpClient) {}

  public getCurrentTraineeEvaluation(): Observable<EvaluationFormModel> {
    return this.httpClient.get<EvaluationFormModel>(GET_CURRENT_EVALUATION_FORM_FOR_TRAINEE_URL);
  }

  public createEvaluationForm(formModel: EvaluationFormModel): Observable<EvaluationFormModel> {
    return this.httpClient.post<EvaluationFormModel>(CREATE_EVALUATION_FORM_URL, formModel);
  }

  public updateEvaluationForm(formModel: EvaluationFormModel): Observable<EvaluationFormModel> {
    return this.httpClient.put<EvaluationFormModel>(UPDATE_EVALUATION_FORM_URL, formModel);
  }
}
