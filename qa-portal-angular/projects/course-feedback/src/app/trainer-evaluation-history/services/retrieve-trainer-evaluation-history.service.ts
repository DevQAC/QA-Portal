import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable()
export class RetrieveTrainerEvaluationHistoryService {

  constructor(private httpClient: HttpClient) { }

  getEvalHistory(): Observable<any> {
    return this.httpClient.get('/feedback-api/evaluation/trainer');
  }
}
