import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { take } from 'rxjs/operators';
import { CohortModel } from '../../../../../portal-core/src/app/_common/models/cohort.model';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { GET_COHORTS_URL } from '../models/user.constant';
import { QaHttpService } from 'projects/portal-core/src/app/_common/services/qa-http.service';
import { TrainerModel } from 'projects/portal-core/src/app/_common/models/trainer.model';
import { UserModel } from 'projects/portal-core/src/app/_common/models/user.model';

@Injectable({
  providedIn: 'root'
})
export class CohortService {

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
  };

  constructor(private http: HttpClient, private qaHttp: QaHttpService) { }

  public searchCohorts(search: string): Observable<CohortModel[]> {
    return this.http.get<CohortModel[]>(GET_COHORTS_URL, this.httpOptions).pipe(
      take(1)
    );
  }

  public getCohortById(id: string | number): Observable<CohortModel> {
    return this.qaHttp.get({ ref: 'GET_COHORT_BY_ID', params: { id: id.toString() } });
  }

  public getAvailableTrainersForCohort(): Observable<TrainerModel[]> {
    return this.qaHttp.get({ ref: 'GET_TRAINERS_AVAILABLE_FOR_COHORT'});
  }

  public saveCohort(cohort: CohortModel): Observable<CohortModel> {
    return this.qaHttp.put({ref: 'SAVE_COHORT'}, cohort);
  }

  public addCohort(cohort: CohortModel): Observable<CohortModel> {
    return this.qaHttp.post({ref: 'CREATE_COHORT'}, cohort);
  }


  public getAvailableTraineesByCohortId(id): Observable<UserModel[]> {
    return this.qaHttp.get({ ref: 'GET_AVAILABLE_TRAINEES_BY_COHORT_ID', params: { id: id.toString() } });
  }
}
