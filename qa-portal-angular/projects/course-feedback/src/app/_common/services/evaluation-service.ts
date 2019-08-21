import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {
  CREATE_EVALUATION_FORM_URL,
  GET_CURRENT_EVALUATION_FORM_FOR_TRAINEE_URL,
  UPDATE_EVALUATION_FORM_URL
} from '../models/course-feedback.constants';
import { IFormModel } from 'projects/qa-forms/src/app/_common/models';

@Injectable()
export class EvaluationService {

  constructor(private httpClient: HttpClient) { }

  public getCurrentTraineeEvaluation(): Observable<IFormModel> {
    return this.httpClient.get<IFormModel>(GET_CURRENT_EVALUATION_FORM_FOR_TRAINEE_URL);
  }

  public createEvaluationForm(formModel: IFormModel): Observable<IFormModel> {
    return this.httpClient.post<IFormModel>(CREATE_EVALUATION_FORM_URL, formModel);
  }

  public updateEvaluationForm(formModel: IFormModel): Observable<IFormModel> {
    return this.httpClient.put<IFormModel>(UPDATE_EVALUATION_FORM_URL, formModel);
  }
}
