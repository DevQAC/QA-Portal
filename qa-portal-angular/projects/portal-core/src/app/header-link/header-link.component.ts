import { Component, Input } from '@angular/core';
import { PortalApplicationProjectsModel } from '../_common/models/portal-application-projects.model';

@Component({
  selector: 'app-header-link',
  templateUrl: './header-link.component.html'
})
export class HeaderLinkComponent {
  @Input() public portalApplicationProjects: PortalApplicationProjectsModel;
}
