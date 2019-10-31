import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { QaHttpService } from 'projects/portal-core/src/app/_common/services/qa-http.service';
import { PortalApplicationModel } from 'projects/portal-core/src/app/_common/models/portal-application.model';
import { PortalApplicationProjectsModel } from 'projects/portal-core/src/app/_common/models/portal-application-projects.model';
import { PortalProjectModel } from 'projects/portal-core/src/app/_common/models/portal-project.model';

@Injectable({
  providedIn: 'root'
})
export class ApplicationService {

  constructor(private qaHttp: QaHttpService) { }

  // Application

  public getAllApplications(): Observable<PortalApplicationModel[]> {
    return this.qaHttp.get({ ref: 'GET_ALL_APPLICATIONS' });
  }

  public addApplication(app: PortalApplicationModel): Observable<PortalApplicationModel> {
    return this.qaHttp.post({ref: 'CREATE_APPLICATION'}, app);
  }

  public getApplicationById(id: string | number): Observable<PortalApplicationProjectsModel> {
    return this.qaHttp.get({ ref: 'GET_APPLICATION_BY_ID', params: { id: id.toString() } });
  }

  public saveApplication(app: PortalApplicationModel): Observable<PortalApplicationModel> {
    return this.qaHttp.put({ ref: 'SAVE_APPLICATION' }, app);
  }


  // Project

  public getAllProjects(): Observable<PortalProjectModel[]> {
    return this.qaHttp.get({ ref: 'GET_ALL_PORTAL_PROJECTS' });
  }

  public addProject(project: PortalProjectModel): Observable<PortalProjectModel> {
    return this.qaHttp.post({ ref: 'CREATE_PORTAL_PROJECT' }, project);
  }

  public getProjectById(id: string | number): Observable<PortalProjectModel> {
    return this.qaHttp.get({ ref: 'GET_PORTAL_PROJECT_BY_ID', params: { id: id.toString() } });
  }

  public saveProject(project: PortalProjectModel): Observable<PortalProjectModel> {
    return this.qaHttp.put({ ref: 'SAVE_PORTAL_PROJECT' }, project);
  }
}
