import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable()
export class TrainerCourseHistoryService {

  constructor(private httpClient: HttpClient) { }

  getCourseHistory(): Observable<any> {
    return this.httpClient.get('/feedback-api/evaluation/trainer');
  }
}
