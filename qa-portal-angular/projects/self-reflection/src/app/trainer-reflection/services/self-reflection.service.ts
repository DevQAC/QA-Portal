import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import {
  REFLECTION_API,
  REFLECTION_QUESTION_API,
  QUESTION_API, USER_API, QUESTION_CATEGORY_API
} from 'projects/portal-core/src/app/_common/models/portal-constants';
import { ReflectionModel } from '../models/dto/reflection.model';
import { ReflectionQuestionModel } from '../models/dto/reflection-question.model';
import { TraineeModel } from '../models/dto/trainee.model';
import {QuestionModel} from "../../_common/models/question.model";

@Injectable()
export class SelfReflectionService {

  constructor(private http: HttpClient) { }

  public getReflectionById(id: number): Observable<ReflectionModel> {
    return this.http.get<ReflectionModel>(`${REFLECTION_API}/${id}`);
  }

  public getReflectionsByTraineeUserName(userName: string): Observable<ReflectionModel[]> {
    return this.http.get<ReflectionModel[]>(`${REFLECTION_API}/trainee/username/${userName}`);
  }

  public getReflectionsByTraineeId(traineeId: number): Observable<ReflectionModel[]> {
    return this.http.get<ReflectionModel[]>(`${REFLECTION_API}/trainee/${traineeId}`);
  }

  public getReflectionQuestionsByReflectionId(reflectionId: number): Observable<ReflectionQuestionModel[]> {
    return this.http.get<ReflectionQuestionModel[]>(`${REFLECTION_QUESTION_API}/reflection-id/${reflectionId}`);
  }

  public updateReflectionQuestions(reflectionQuestions: ReflectionQuestionModel[]): Observable<ReflectionQuestionModel[]> {
    return this.http.put<ReflectionQuestionModel[]>(`${REFLECTION_QUESTION_API}`, reflectionQuestions);
  }

  public updateReflection(reflection: ReflectionModel): Observable<ReflectionModel> {
    return this.http.put<ReflectionModel>(`${REFLECTION_API}`, reflection);
  }

  public getQuestionCategoriesByFormType(formType: string): Observable<any[]> {
    return this.http.get<any[]>(`${QUESTION_CATEGORY_API}/${formType}`);
  }

  public getQuestionsByFormType(formType: string): Observable<QuestionModel[]> {
    return this.http.get<QuestionModel[]>(`${QUESTION_API}/${formType}`);
  }

  // TODO: move to portal-common
  public getTraineeById(traineeId: number): Observable<TraineeModel> {
    return this.http.get<TraineeModel>(`${USER_API}/trainee/${traineeId}`);
  }

}
