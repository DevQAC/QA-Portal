import { Injectable } from '@angular/core';
import { QaHttpService } from 'projects/portal-core/src/app/_common/services/qa-http.service';
import { Observable } from 'rxjs';
import { CourseModel } from 'projects/portal-core/src/app/_common/models/course.model';

@Injectable({
  providedIn: 'root'
})
export class CourseService {

  constructor(private qaHttp: QaHttpService) { }

  public getAllCourses(): Observable<CourseModel[]> {
    return this.qaHttp.get<CourseModel[]>({ref: 'GET_ALL_COURSES'});
  }
}
