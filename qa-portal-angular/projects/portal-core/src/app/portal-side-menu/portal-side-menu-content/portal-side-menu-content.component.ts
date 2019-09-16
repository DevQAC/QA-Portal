import { Component, EventEmitter, Input, Output } from '@angular/core';
import { SideMenuService } from '../../_common/services/side-menu.service';
import { ApplicationService } from '../../_common/services/application.service';

@Component({
  selector: 'app-portal-side-menu-content',
  templateUrl: './portal-side-menu-content.component.html',
  styleUrls: ['./portal-side-menu-content.component.css']
})
export class PortalSideMenuContentComponent {
  @Input() opened: boolean;
  @Output() openedDrawerEmmiter = new EventEmitter();

  constructor(
    public appService: ApplicationService,
    public sideMenuService: SideMenuService) { }

  toggleDrawer() {
    this.opened = !this.opened;
    this.openedDrawerEmmiter.emit(this.opened);
  }
}
