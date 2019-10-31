import {ProjectPageModel} from './project-page.model';

export class PortalProjectModel {
  id: number;
  name: string;
  url: string;
  projectPages: ProjectPageModel[] = [];
}
