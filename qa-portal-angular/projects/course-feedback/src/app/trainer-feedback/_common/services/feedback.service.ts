import {Observable} from 'rxjs';
// import {IFormModel} from '../../../../../../qa-forms/src/app/_common/models/form-model';
import {HttpClient} from '@angular/common/http';
import {
  CREATE_FEEDBACK_FORM_URL,
  GET_FEEDBACK_FOR_COURSE_URL,
  UPDATE_FEEDBACK_FORM_URL
} from '../../../_common/models/course-feedback.constants';
import {Injectable} from '@angular/core';
import { IFormModel } from 'projects/qa-forms/src/app/_common/models';

@Injectable()
export class FeedbackService {

  constructor(private httpClient: HttpClient) {}

  public getFeedbackforCourse(courseId: number): Observable<IFormModel> {
    return this.httpClient.get<IFormModel>(GET_FEEDBACK_FOR_COURSE_URL + courseId);
  }

  public createFeedbackForm(feedbackForm: IFormModel): Observable<IFormModel> {
    return this.httpClient.post<IFormModel>(CREATE_FEEDBACK_FORM_URL, feedbackForm);
  }

  public updateFeedbackForm(feedbackForm: IFormModel): Observable<IFormModel> {
    return this.httpClient.put<IFormModel>(UPDATE_FEEDBACK_FORM_URL, feedbackForm);
  }
}
