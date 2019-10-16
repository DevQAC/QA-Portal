import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { QaHttpService } from 'projects/portal-core/src/app/_common/services/qa-http.service';
import { PortalApplicationProjectsModel } from 'projects/portal-core/src/app/_common/models/portal-application-projects.model';

@Injectable({
  providedIn: 'root'
})
export class ApplicationService {

  constructor(private qaHttp: QaHttpService) { }

  public getAllApplications(): Observable<PortalApplicationProjectsModel[]> {
    return this.qaHttp.get({ref: 'GET_ALL_APPLICATIONS'});
  }
}
