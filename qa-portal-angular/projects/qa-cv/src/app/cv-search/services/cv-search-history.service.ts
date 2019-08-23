import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Observable, of} from "rxjs";
import {CVSearchModel} from '../models/cv-search-model';
import {CVS} from '../models/mock-cv';


@Injectable()
export class CVSearchHistoryService {

    constructor(private http: HttpClient){}

    public getAdminSearches(): Observable<CVSearchModel[]> {
        return of(CVS)
    }

}
