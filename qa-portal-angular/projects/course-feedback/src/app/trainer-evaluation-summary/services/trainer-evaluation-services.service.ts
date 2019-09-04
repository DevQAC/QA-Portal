import { Injectable } from '@angular/core';
import {GET_ALL_FEEDBACK_API} from '../models/trainer-evaluation-summary-constants';
import {HttpClient} from '@angular/common/http';
import {trainerEvaluationSummaryForm} from '../models/trainer-evaluation-summary-form';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TrainerEvaluationServicesService {

  constructor(private httpClient: HttpClient) {}

  public getTrainerEvaluationSummary(): Observable<trainerEvaluationSummaryForm> {
    return this.httpClient.get<trainerEvaluationSummaryForm>(GET_ALL_FEEDBACK_API);
  }
}
