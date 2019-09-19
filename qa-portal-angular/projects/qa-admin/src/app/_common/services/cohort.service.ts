import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {take} from 'rxjs/operators';
import {CohortModel} from '../../../../../portal-core/src/app/_common/models/cohort.model';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {DELETE_COHORTS_URL, GET_COHORTS_URL} from '../models/user.constant';

@Injectable({
  providedIn: 'root'
})
export class CohortService {

  httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'}),
  };

  constructor(private http: HttpClient) { }

  public searchCohorts(search: string): Observable<CohortModel[]> {
    return this.http.get<CohortModel[]>(GET_COHORTS_URL, this.httpOptions).pipe(
      take(1)
    );
  }

  public deleteCohorts(cohorts: CohortModel[]) {
    // Functionality no longer required
  }
}
