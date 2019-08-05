import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {GET_SELF_REFLECTIONS_FOR_TRAINEE_API} from "../../_common/models/trainee-reflection-constants";
import {Observable} from "rxjs";
import {SelfReflectionFormModel} from "../../_common/models/self-reflection-form-model";



@Injectable()
export class ReflectionHistoryService {

    constructor(private http: HttpClient){}

    public getTraineeReflections(): Observable<SelfReflectionFormModel[]> {
        return this.http.get<SelfReflectionFormModel[]>(GET_SELF_REFLECTIONS_FOR_TRAINEE_API);
    }

}
