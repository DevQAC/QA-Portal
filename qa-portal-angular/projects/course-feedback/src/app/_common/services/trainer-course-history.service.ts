import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {TrainerCourseHistoryModel} from '../../trainer-evaluation-history/models/trainer-course.history.model';

@Injectable()
export class TrainerCourseHistoryService {

  constructor(private httpClient: HttpClient) { }

  getCourseHistory(): Observable<TrainerCourseHistoryModel> {
    return this.httpClient.get<TrainerCourseHistoryModel>('/feedback-api/evaluation/trainer');
  }
}
