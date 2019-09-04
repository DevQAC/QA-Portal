import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TrainerEvaluationServicesService {

  constructor(private httpClient: HttpClient) {}

  public GetTrainerFeedback(courseId): Observable<any> {
    return this.httpClient.get<any>('feedback-api/evaluation/course/' + courseId);
  }
}
