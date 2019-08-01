import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {SelfReflectionFormModel} from '../../_common/models/self-reflection-form-model';
import {Observable} from 'rxjs';
import {
  CREATE_SELF_REFLECTION_API,
  GET_SELF_REFLECTION_API,
  UPDATE_SELF_REFLECTION_API
} from '../../_common/models/trainee-reflection-constants';

@Injectable()
export class SelfReflectionFormService {

  constructor(private httpClient: HttpClient) {}

  public createSelfReflectionForm(form: SelfReflectionFormModel): Observable<SelfReflectionFormModel> {
    return this.httpClient.post<SelfReflectionFormModel>(CREATE_SELF_REFLECTION_API , form);
  }

  public getSelfReflectionForm(formId: string): Observable<SelfReflectionFormModel> {
    return this.httpClient.get<SelfReflectionFormModel>(GET_SELF_REFLECTION_API + '/' + formId);
  }

  public updateSelfReflectionForm(form: SelfReflectionFormModel): Observable<SelfReflectionFormModel> {
    return this.httpClient.put<SelfReflectionFormModel>(UPDATE_SELF_REFLECTION_API, form);
  }
}
