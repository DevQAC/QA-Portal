import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { DataModel } from '../Models/data.model';
import { GET_FORM_TYPE } from '../Models/question_url_constants';

@Injectable()
export class FormTypeService {

  constructor(private httpClient: HttpClient) { }

  getFormType(formName: string): Observable<DataModel>{
    return this.httpClient.get<DataModel>(GET_FORM_TYPE + formName);
  }
}
