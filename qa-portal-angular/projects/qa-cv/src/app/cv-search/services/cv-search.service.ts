import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from "rxjs";
import { CVSearchModel } from '../models/cv-search-model';
import { CVS } from '../models/mock-cv';
import { catchError, map, tap } from 'rxjs/operators';

@Injectable()
export class CVSearchHistoryService {

    constructor(private http: HttpClient) { }

    private searchUrl = 'cv-api/cv/search';  // URL to cv-api

    /**
 * Handle Http operation that failed.
 * Let the app continue.
 * @param operation - name of the operation that failed
 * @param result - optional value to return as the observable result
 */
    private handleError<T>(operation = 'operation', result?: T) {
        return (error: any): Observable<T> => {

            console.error(error); // log to console instead

            // Let the app keep running by returning an empty result.
            return of(result as T);
        };
    }
    public getCVSearches(term: string, intakeChoice: string = "", techChoice: string = "", statusChoice: string = ""): Observable<CVSearchModel[]> {
        return of(CVS.filter((cv) => {
            let outcome = false;
            if (cv.fullName == term) {
                if (intakeChoice != "") {
                    if (intakeChoice == cv.cohort) {
                        outcome = true;
                    }
                    else {
                        return false;
                    }
                }
                if (techChoice != "") {
                    if (techChoice == cv.allSkills[0]) {
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

    /* GET cvs whose name contains search term */
    searchCVs(term: string, intakeChoice: string = "", techChoice: string = "", statusChoice: string = ""): Observable<CVSearchModel[]> {
        const params = new URLSearchParams();
        if(term) {
            params.set('name', term);
        }
        if(intakeChoice) {
            params.set('cohort', intakeChoice);
        }
        if(techChoice) {
            params.set('tech', techChoice);
        }
        if(statusChoice) {
            params.set('status', statusChoice);
        }

        return this.http.get<CVSearchModel[]>(`${this.searchUrl}?${params.toString()}`).pipe(
            catchError(this.handleError<CVSearchModel[]>('searchCVs', []))
        );
    }
}
