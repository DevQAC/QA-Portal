import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { DataModel } from '../models/data.model';
import { GET_FORM_TYPE, SEND_EVAL_RESPONSE } from '../models/question_url_constants';

@Injectable()
export class FormTypeService {

  constructor(private httpClient: HttpClient) { }

  getFormType(formName: string): Observable<DataModel[]> {
    return this.httpClient.get<DataModel[]>(GET_FORM_TYPE + formName);
  }

  /**
   *This method is used to send the user's end of course feedback response back to the data, currently this method acts as both the save and submit function,
   * though the sent body does not feature a completed property.
   * Also current SEND_EVAL_RESPONSE is set to " "
   *
   * @param {DataModel[]} userResponse
   * @memberof FormTypeService
   */
  sendEvalForm(userResponse: DataModel[]): void {
    this.httpClient.post(SEND_EVAL_RESPONSE, userResponse);
  }
}
