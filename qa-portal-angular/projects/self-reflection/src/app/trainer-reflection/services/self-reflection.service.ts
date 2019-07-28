import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { REFLECTION_API, REFLECTION_QUESTION_API, QUESTION_API } from 'projects/portal-core/src/app/_common/models/portal-constants';
import { Reflection } from '../models/dto/reflection';
import { ReflectionQuestion } from '../models/dto/reflection-question';
import { Question } from '../models/dto/question';

@Injectable({
  providedIn: 'root'
})
export class SelfReflectionService {

  constructor(private http: HttpClient) { }

  public getReflectionsForCurrentTrainee(): Observable<Reflection[]> {
    return this.http.get<Reflection[]>(`${REFLECTION_API}/trainee/current`);
  }

  public getReflectionsForCurrentTrainer(): Observable<Reflection[]> {
    return this.http.get<Reflection[]>(`${REFLECTION_API}/trainer/current`);
  }

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

  public create(reflection: Reflection): Observable<Reflection> {
    return this.http.post<Reflection>(REFLECTION_API, reflection);
  }

  public getQuestions(cohortId: number): Observable<Question[]> {
    return this.http.get<Question[]>(`${QUESTION_API}/cohort/${cohortId}`);
    // return of([
    //   {
    //     category: '1',
    //     body: 'How well have you been able to use the technologies and tools you have learnt this week to solve a solution?'
    //   },
    //   {
    //     category: '2',
    //     body: 'How well would you be able to explain the concepts you have learnt this week to a peer?'
    //   },
    //   {
    //     category: '3',
    //     body: 'How well have you driven high standards through collaboration and teamwork this week?'
    //   },
    //   {
    //     category: '4',
    //     body: 'How well have you been able to present ideas and concepts to the group this week?'
    //   },
    //   {
    //     category: '5',
    //     body: 'How well have you managed your time this week at the Academy?'
    //   },
    //   {
    //     category: '6',
    //     body: 'How ambitious have you been to work on projects out of the Academy to improve your skills?'
    //   },
    // ]);
  }
}
