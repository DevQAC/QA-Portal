import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { QaHttpService } from 'projects/portal-core/src/app/_common/services/qa-http.service';
import { PortalApplicationModel } from 'projects/portal-core/src/app/_common/models/portal-application.model';
import { PortalApplicationProjectsModel } from 'projects/portal-core/src/app/_common/models/portal-application-projects.model';

@Injectable({
  providedIn: 'root'
})
export class ApplicationService {

  constructor(private qaHttp: QaHttpService) { }

  public getAllApplications(): Observable<PortalApplicationModel[]> {
    return this.qaHttp.get({ ref: 'GET_ALL_APPLICATIONS' });
  }

  public getApplicationById(id: string | number): Observable<PortalApplicationProjectsModel> {
    return this.qaHttp.get({ ref: 'GET_APPLICATION_BY_ID', params: { id: id.toString() } });
  }

  public saveApplication(app: PortalApplicationModel): Observable<PortalApplicationModel> {
    return this.qaHttp.put({ ref: 'SAVE_APPLICATION' }, app);
  }


  public getAllProjects(): Observable<PortalApplicationProjectsModel[]> {
    return this.qaHttp.get({ref: 'GET_ALL_PORTAL_PROJECTS'});
  }
}
