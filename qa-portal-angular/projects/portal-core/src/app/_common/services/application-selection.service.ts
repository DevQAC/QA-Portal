import {Injectable} from '@angular/core';
import {PortalProjectModel} from '../models/portal-project.model';
import {Observable, Subject} from 'rxjs';
import { PortalApplicationProjectsModel } from '../models/portal-application-projects.model';

@Injectable()
export class ApplicationSelectionService {

  private selectedProject: Subject<PortalProjectModel> = new Subject();
  private selectedApplication: Subject<PortalApplicationProjectsModel> = new Subject();

  constructor() {
  }

  public getSelectedProject$(): Observable<PortalProjectModel> {
    return this.selectedProject.asObservable();
  }

  public setSelectedProject(app: PortalProjectModel) {
    this.selectedProject.next(app);
  }

  public getSelectedApplication$(): Observable<PortalApplicationProjectsModel> {
    return this.selectedApplication.asObservable();
  }

  public setSelectedApplication(dep: PortalApplicationProjectsModel) {
    this.selectedApplication.next(dep);
  }
}
