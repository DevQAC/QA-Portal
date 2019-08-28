import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from "rxjs";
import { FilterModel } from '../models/filter-search-model';
import { FILTERS } from '../models/mock-filters';
import { catchError, map, tap } from 'rxjs/operators';


@Injectable()
export class CVSearchFilterService {

    constructor(private http: HttpClient) { }

    private filterUrl = 'api/filter';  // URL to web api

    /**
 * Handle Http operation that failed.
 * Let the app continue.
 * @param operation - name of the operation that failed
 * @param result - optional value to return as the observable result
 */
    private handleError<T>(operation = 'operation', result?: T) {
        return (error: any): Observable<T> => {

            // TODO: send the error to remote logging infrastructure
            console.error(error); // log to console instead

            // Let the app keep running by returning an empty result.
            return of(result as T);
        };
    }

    public getFiltersSearches(): Observable<FilterModel> {
        return of(FILTERS)
    }

    /* GET filter dropdowns */
    getFilters(): Observable<FilterModel> {

        return this.http.get<FilterModel>(`${this.filterUrl}`).pipe(

            catchError(this.handleError<FilterModel>('getFilters', {
                technology: [],
                cvStatus: [],
                cohort: []
            }))
        );
    }
}
