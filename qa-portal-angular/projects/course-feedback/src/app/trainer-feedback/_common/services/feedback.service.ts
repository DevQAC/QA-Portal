import {Observable} from 'rxjs';
// import {IFormModel} from '../../../../../../qa-forms/src/app/_common/models/form-model';
import {HttpClient} from '@angular/common/http';
import {
  CREATE_FEEDBACK_FORM_URL,
  GET_FEEDBACK_FOR_COURSE_URL,
  UPDATE_FEEDBACK_FORM_URL
} from '../../../_common/models/course-feedback.constants';
import {Injectable} from '@angular/core';
import {FeedbackFormModel} from '../../../_common/models/feedback-form.model';

@Injectable()
export class FeedbackService {

  constructor(private httpClient: HttpClient) {}

  public getFeedbackforCourse(courseId: number): Observable<FeedbackFormModel> {
    return this.httpClient.get<FeedbackFormModel>(GET_FEEDBACK_FOR_COURSE_URL + courseId);
  }

  public createFeedbackForm(feedbackForm: FeedbackFormModel): Observable<FeedbackFormModel> {
    return this.httpClient.post<FeedbackFormModel>(CREATE_FEEDBACK_FORM_URL, feedbackForm);
  }

  public updateFeedbackForm(feedbackForm: FeedbackFormModel): Observable<FeedbackFormModel> {
    return this.httpClient.put<FeedbackFormModel>(UPDATE_FEEDBACK_FORM_URL, feedbackForm);
  }
}
