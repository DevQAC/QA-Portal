import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { REFLECTION_API, REFLECTION_QUESTION_API, QUESTION_API } from 'projects/portal-core/src/app/_common/models/portal-constants';
import { Reflection } from '../models/dto/reflection';
import { ReflectionQuestion } from '../models/dto/reflection-question';
import { Question } from '../models/dto/question';

@Injectable()
export class SelfReflectionService {

  constructor(private http: HttpClient) { }

  public getReflectionById(id: number): Observable<Reflection> {
    return this.http.get<Reflection>(`${REFLECTION_API}/${id}`);
  }

  public getReflectionsByTraineeUserName(userName: string): Observable<Reflection[]> {
    return this.http.get<Reflection[]>(`${REFLECTION_API}/trainee/username/${userName}`);
  }

  public getReflectionsByTraineeId(traineeId: number): Observable<Reflection[]> {
    return this.http.get<Reflection[]>(`${REFLECTION_API}/trainee/${traineeId}`);
  }

  public getReflectionQuestionsByReflectionId(reflectionId: number): Observable<ReflectionQuestion[]> {
    return this.http.get<ReflectionQuestion[]>(`${REFLECTION_QUESTION_API}/reflection-id/${reflectionId}`);
  }

  public updateReflectionQuestions(reflectionQuestions: ReflectionQuestion[]): Observable<ReflectionQuestion[]> {
    return this.http.put<ReflectionQuestion[]>(`${REFLECTION_QUESTION_API}`, reflectionQuestions);
  }

  public getQuestionsByCohortId(cohortId: number): Observable<Question[]> {
    return this.http.get<Question[]>(`${QUESTION_API}/cohort/${cohortId}`);
  }
}
