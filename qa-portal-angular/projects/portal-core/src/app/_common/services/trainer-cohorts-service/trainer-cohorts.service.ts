import { Injectable } from '@angular/core';
import {TRAINER_COHORTS_API} from '../../models/portal-constants';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable()
export class TrainerCohortsService {

  constructor(private httpClient: HttpClient) { }

  getPortalMenu(): Observable<any[]> {
    return this.httpClient.get<any[]>(TRAINER_COHORTS_API);
  }
}
