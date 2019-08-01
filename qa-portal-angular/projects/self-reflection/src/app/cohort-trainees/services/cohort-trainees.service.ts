import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import { Observable } from 'rxjs';
import { QaCohortModel } from '../models/qa-cohort.model';
import { USER_API_COHORTS,USER_API_TRAINEES } from 'projects/portal-core/src/app/_common/models/portal-constants';
import { TraineeModel } from '../models/trainee.model';

@Injectable()
export class CohortTraineesService {
  constructor(private http: HttpClient) {
  }

  getCohorts(): Observable<QaCohortModel[]> {
    return this.http.get<QaCohortModel[]>(`${USER_API_COHORTS}`);
  }

  getTrainees(id: number): Observable<TraineeModel[]> {
    return this.http.get<TraineeModel[]>(`${USER_API_TRAINEES}${id}`);
  }
}
