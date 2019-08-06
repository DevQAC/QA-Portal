import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { SelfReflectionFormModel } from '../../_common/models/self-reflection-form-model';
import { Observable } from 'rxjs';
import {
  CREATE_SELF_REFLECTION_API,
  GET_SELF_REFLECTION_API, 
  GET_SELF_REFLECTIONS_BY_STATUS_API,
  CREATE_SELF_REFLECTION_QUESTIONS_API,
  UPDATE_SELF_REFLECTION_API
} from '../../_common/models/trainee-reflection-constants';
import { ReflectionQuestionModel } from '../../_common/models/reflection.question.model';

@Injectable()
export class SelfReflectionFormService {

  constructor(private httpClient: HttpClient) { }

  public createSelfReflectionForm(form: SelfReflectionFormModel): Observable<SelfReflectionFormModel> {
    return this.httpClient.post<SelfReflectionFormModel>(CREATE_SELF_REFLECTION_API , form);
  }

  public getSelfReflectionForm(formId: string): Observable<SelfReflectionFormModel> {
    return this.httpClient.get<SelfReflectionFormModel>(GET_SELF_REFLECTION_API + '/' + formId);
  }
  
  public getAllReflectionFormsForUser(): Observable<Set<SelfReflectionFormModel>> {
    return this.httpClient.get<Set<SelfReflectionFormModel>>(GET_SELF_REFLECTION_API + '/trainee');
  }

  public getSelfReflectionFormByStatus(status: string): Observable<SelfReflectionFormModel> {
    return this.httpClient.get<SelfReflectionFormModel>(GET_SELF_REFLECTIONS_BY_STATUS_API + '/' + status);
  }

  public updateSelfReflectionForm(form:SelfReflectionFormModel):Observable<SelfReflectionFormModel> {
    return this.httpClient.put<SelfReflectionFormModel>(UPDATE_SELF_REFLECTION_API, form);
  }

  public createSelfReflectionQuestions(reflectionQuestions: ReflectionQuestionModel[]): Observable<ReflectionQuestionModel[]> {
    return this.httpClient.post<ReflectionQuestionModel[]>(CREATE_SELF_REFLECTION_QUESTIONS_API, reflectionQuestions);
  }
  
  public getCurrentSelfReflectionForm(): Observable<SelfReflectionFormModel> {
    return this.httpClient.get<SelfReflectionFormModel>(GET_SELF_REFLECTION_API);
  }
  
}
