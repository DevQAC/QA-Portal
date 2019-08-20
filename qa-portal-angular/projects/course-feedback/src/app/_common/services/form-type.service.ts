import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { QuestionCategoryModel } from '../models/question-category.model';
import { GET_FORM_TYPE, SEND_EVAL_RESPONSE } from '../models/question-url.constants';
import { ICategory } from 'projects/qa-forms/src/app/_common/models/form-category.model';

@Injectable()
export class FormTypeService {

  constructor(private httpClient: HttpClient) { }

  getFormType(formName: string): Observable<ICategory[]> {
    return this.httpClient.get<ICategory[]>(GET_FORM_TYPE + formName);
  }

  /**
   *This method is used to send the user's end of course feedback response back to the data, currently this method acts as both the save and submit function,
   * though the sent body does not feature a completed property.
   * Also current SEND_EVAL_RESPONSE is set to " "
   *
   * @param {ICategory[]} form
   * @memberof FormTypeService
   */
  sendEvalForm(form: ICategory[]): Observable<ICategory> {
    return this.httpClient.post<ICategory>(SEND_EVAL_RESPONSE, form);
  }
}
