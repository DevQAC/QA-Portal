import { Component } from '@angular/core';
import { SideMenuService } from '../_common/services/side-menu.service';

@Component({
  selector: 'app-portal-side-menu',
  templateUrl: './portal-side-menu.component.html'
})
export class PortalSideMenuComponent {
  constructor(public sideMenuService: SideMenuService) { }
}
