import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {TrainerCourseHistoryModel} from '../models/trainer-course.history.model';
import {GET_COHORT_COURSES_FOR_TRAINER} from '../../_common/models/course-feedback.constants';

@Injectable()
export class RetrieveTrainerEvaluationHistoryService {

  constructor(private httpClient: HttpClient) { }

  getEvalHistory(): Observable<TrainerCourseHistoryModel> {
    return this.httpClient.get<TrainerCourseHistoryModel>(GET_COHORT_COURSES_FOR_TRAINER);
  }
}
