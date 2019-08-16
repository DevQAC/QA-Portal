import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {GET_COURSE_EVALUATION_FOR_TRAINEE_API} from "../../_common/models/.";
import {Observable} from "rxjs";
import {CourseEvaluationFormModel} from "../../_common/models/course-evaluation-form.model";



@Injectable()
export class CourseEvaluationService {

    constructor(private http: HttpClient){}

    public getTraineeReflections(): Observable<CourseEvaluationFormModel[]> {
        return this.http.get<CourseEvaluationFormModel[]>(GET_COURSE_EVALUATION_FOR_TRAINEE_API);
    }

}
