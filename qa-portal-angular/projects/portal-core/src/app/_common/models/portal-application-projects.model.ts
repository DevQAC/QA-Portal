import { PortalApplicationModel } from './portal-application.model';
import { PortalProjectModel } from './portal-project.model';

export class PortalApplicationProjectsModel {
  portalApplication: PortalApplicationModel;

  portalProjects: PortalProjectModel[] = [];
}
