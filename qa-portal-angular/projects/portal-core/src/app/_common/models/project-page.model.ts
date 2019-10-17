export class ProjectPageModel {
  id: number;
  name: string;
  url: string;
  tooltip: string;
  level: number;
  displayOnMenu: boolean;
  icon: string;
  portalProjectName: string;
  roles: string[] = [];
}
