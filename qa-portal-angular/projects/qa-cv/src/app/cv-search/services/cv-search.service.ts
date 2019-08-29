import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from "rxjs";
import { CVSearchModel } from '../models/cv-search-model';
import { CVS } from '../models/mock-cv';
import { catchError, map, tap } from 'rxjs/operators';

@Injectable()
export class CVSearchHistoryService {

    constructor(private http: HttpClient) { }

    private searchUrl = 'api/filter';  // URL to web api

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
    public getCVSearches(term: string, intakeChoice: string = "", techChoice: string = "", statusChoice: string = ""): Observable<CVSearchModel[]> {
        return of(CVS.filter((cv) => {
            let outcome = false;
            if (cv.name == term) {
                if (intakeChoice != "") {
                    if (intakeChoice == cv.intake) {
                        outcome = true;
                    }
                    else {
                        return false;
                    }
                }
                if (techChoice != "") {
                    if (techChoice == cv.tech) {
                        outcome = true;
                    }
                    else {
                        return false;
                    }
                }
                if (statusChoice != "") {
                    if (statusChoice == cv.status) {
                        outcome = true;
                    }
                    else {
                        return false;
                    }
                }
                if (intakeChoice == "" && techChoice == "" && statusChoice == "") {
                    outcome = true;
                }
            }
            return outcome;
        }));
    }

    /* GET heroes whose name contains search term */
    searchCVs(term: string, intakeChoice: string = "", techChoice: string = "", statusChoice: string = ""): Observable<CVSearchModel[]> {
        if (!term.trim()) {
            // if not search term, return empty CV array.
            return of([]);
        }
        return this.http.get<CVSearchModel[]>(`${this.searchUrl}/?name=${term}/?cohort=${intakeChoice}/?technology=${techChoice}/?cvStatus=${statusChoice}`).pipe(
            catchError(this.handleError<CVSearchModel[]>('searchCVs', []))
        );
    }
}
