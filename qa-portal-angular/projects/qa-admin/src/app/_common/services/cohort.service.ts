import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { ICohort } from '../models/cohort.model';
import { DUMMY_COHORTS } from './dummy-cohort-data';
import { delay } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class CohortService {

  constructor() { }

  public searchCohorts(search: string): Observable<ICohort[]> {
    console.warn('CohortService.searchCohorts is using dummy data!');
    return of(
      DUMMY_COHORTS
        .filter(c => c.cohortName.toLowerCase().includes(search.toLowerCase()) ||
          c.trainer.toLowerCase().includes(search.toLowerCase()))
    ).pipe(delay(Math.floor(Math.random() * 3500) + 500));
  }
}
