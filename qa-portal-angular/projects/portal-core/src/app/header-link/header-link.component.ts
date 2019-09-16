import {Component, Input, OnInit} from '@angular/core';
import { PortalApplicationProjectsModel } from '../_common/models/portal-application-projects.model';

@Component({
  selector: 'app-header-link',
  templateUrl: './header-link.component.html',
  styleUrls: ['./header-link.component.css']
})
export class HeaderLinkComponent {
  @Input() portalApplicationProjects: PortalApplicationProjectsModel;

  getFirstUrl() {
    return this.portalApplicationProjects.portalProjects[0].projectPages.find((pp) => pp.displayOnMenu).url;
  }
}
