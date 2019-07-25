import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { SkillArea } from '../models/skill-area';
import { User } from '../models/User.enum';
import { HttpClient } from '@angular/common/http';
import { SELF_REFLECTION_API } from 'projects/portal-core/src/app/_common/models/portal-constants';
import { Reflection } from '../models/dto/reflection';

@Injectable({
  providedIn: 'root'
})
export class SelfReflectionService {

  constructor(private http: HttpClient) { }

  public getTraineeReflection(): Observable<SkillArea[]> {
    return of(this.traineeReflectionData());
  }

  public getCurrent(): Observable<any> {
    return this.http.get<any>(`${SELF_REFLECTION_API}/current`);
  }
  public getById(id: number): Observable<any> {
    return this.http.get<any>(`${SELF_REFLECTION_API}/${id}`);
  }

  public create(reflection: Reflection): Observable<Reflection> {
    return this.http.post<Reflection>(SELF_REFLECTION_API, reflection);
  }

  private traineeReflectionData(): SkillArea[] {
    const dates = [new Date(5), new Date(4), new Date(3), new Date(2), new Date(1)];
    return [
      {
        name: 'Technical Skills',
        forms: [
          {
            id: 1,
            text: 'How well have you been able to use the technologies and tools you have learnt this week to solve a solution?',
            userScores:
              [{
                author: User.TRAINEE,
                scores: [
                  { value: 8, weekCommencing: dates[0] },
                  { value: 7, weekCommencing: dates[4] },
                  { value: 8, weekCommencing: dates[3] },
                  { value: 5, weekCommencing: dates[2] },
                  { value: 7, weekCommencing: dates[1] },
                ]
              }]
          },
          {
            id: 2,
            text: 'How well would you be able to explain the concepts you have learnt this week to a peer?',
            userScores:
              [{
                author: User.TRAINEE,
                scores: [
                  { value: 7, weekCommencing: dates[5] },
                  { value: 8, weekCommencing: dates[4] },
                  { value: 8, weekCommencing: dates[3] },
                  { value: 6, weekCommencing: dates[2] },
                  { value: 7, weekCommencing: dates[1] },
                ]
              },
              {
                author: User.TRAINER,
                scores: [
                  { value: 7, weekCommencing: dates[5] },
                  { value: 7, weekCommencing: dates[4] },
                  { value: 6, weekCommencing: dates[3] },
                  { value: 8, weekCommencing: dates[2] },
                  { value: 8, weekCommencing: dates[1] },
                ]
              }]
          }
        ]
      },
      {
        name: 'Soft Skills',
        forms: [
          {
            id: 3,
            text: 'How well have you driven high standards through collaboration and teamwork this week?',
            userScores:
              [{
                author: User.TRAINEE,
                scores: [
                  { value: 6, weekCommencing: dates[5] },
                  { value: 7, weekCommencing: dates[4] },
                  { value: 6, weekCommencing: dates[3] },
                  { value: 6, weekCommencing: dates[2] },
                  { value: 7, weekCommencing: dates[1] },
                ]
              }]
          },
          {
            id: 4,
            text: 'How well have you been able to present ideas and concepts to the group this week?',
            userScores:
              [{
                author: User.TRAINEE,
                scores: [
                  { value: 7, weekCommencing: dates[5] },
                  { value: 6, weekCommencing: dates[4] },
                  { value: 6, weekCommencing: dates[3] },
                  { value: 7, weekCommencing: dates[2] },
                  { value: 7, weekCommencing: dates[1] },
                ]
              },
              {
                author: User.TRAINER,
                scores: [
                  { value: 5, weekCommencing: dates[5] },
                  { value: 5, weekCommencing: dates[4] },
                  { value: 6, weekCommencing: dates[3] },
                  { value: 6, weekCommencing: dates[2] },
                  { value: 6, weekCommencing: dates[1] },
                ]
              }]
          }
        ]
      },
      {
        name: 'Attitude',
        forms: [
          {
            id: 5,
            text: 'How well have you managed your time this week at the Academy?',
            userScores:
              [{
                author: User.TRAINEE,
                scores: [
                  { value: 8, weekCommencing: dates[5] },
                  { value: 9, weekCommencing: dates[4] },
                  { value: 8, weekCommencing: dates[3] },
                  { value: 7, weekCommencing: dates[2] },
                  { value: 8, weekCommencing: dates[1] },
                ]
              }]
          },
          {
            id: 6,
            text: 'How ambitious have you been to work on projects out of the Academy to improve your skills?',
            userScores:
              [{
                author: User.TRAINEE,
                scores: [
                  { value: 7, weekCommencing: dates[5] },
                  { value: 9, weekCommencing: dates[4] },
                  { value: 8, weekCommencing: dates[3] },
                  { value: 7, weekCommencing: dates[2] },
                  { value: 7, weekCommencing: dates[1] },
                ]
              },
              {
                author: User.TRAINER,
                scores: [
                  { value: 7, weekCommencing: dates[5] },
                  { value: 10, weekCommencing: dates[4] },
                  { value: 7, weekCommencing: dates[3] },
                  { value: 6, weekCommencing: dates[2] },
                  { value: 7, weekCommencing: dates[1] },
                ]
              }]
          }
        ]
      }
    ];

  }
}
