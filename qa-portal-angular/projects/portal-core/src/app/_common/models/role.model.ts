import {PortalApplicationModel} from './portal-application.model';

export class RoleModel {
  id: number;

  name: string;

  portalApplication: PortalApplicationModel;
}
