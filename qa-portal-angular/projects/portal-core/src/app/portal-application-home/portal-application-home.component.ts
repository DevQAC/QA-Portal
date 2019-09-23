import { Component } from '@angular/core';
import { SideMenuService } from '../_common/services/side-menu.service';
import { ApplicationService } from '../_common/services/application.service';

@Component({
  selector: 'app-portal-application-home',
  templateUrl: './portal-application-home.component.html'
})
export class PortalApplicationHomeComponent {

  constructor(
    public sideMenuService: SideMenuService,
    public appService: ApplicationService
  ) { }
}
