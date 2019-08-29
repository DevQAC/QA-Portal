import {Observable} from 'rxjs';
import {HttpClient} from '@angular/common/http';
import {
  CREATE_FEEDBACK_FORM_URL,
  GET_FEEDBACK_FOR_COURSE_URL,
  UPDATE_FEEDBACK_FORM_URL
} from '../../../_common/models/course-feedback.constants';
import {Injectable} from '@angular/core';
import {IFormModel} from 'projects/qa-forms/src/app/_common/models';
import {IFormService} from '../../../_common/services/iform.service';

@Injectable()
export class FeedbackService implements IFormService {

  constructor(private httpClient: HttpClient) {
  }

  public getForm(courseId: string): Observable<IFormModel> {
    return this.httpClient.get<IFormModel>(GET_FEEDBACK_FOR_COURSE_URL + courseId);
  }

  public createForm(feedbackForm: IFormModel): Observable<IFormModel> {
    return this.httpClient.post<IFormModel>(CREATE_FEEDBACK_FORM_URL, feedbackForm);
  }

  public updateForm(feedbackForm: IFormModel): Observable<IFormModel> {
    return this.httpClient.put<IFormModel>(UPDATE_FEEDBACK_FORM_URL, feedbackForm);
  }
}
