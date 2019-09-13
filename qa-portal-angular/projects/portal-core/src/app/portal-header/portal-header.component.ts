import { Component, EventEmitter, OnDestroy, OnInit, Output } from '@angular/core';
import { MenuService } from '../_common/services/menu.service';
import { Subscription } from 'rxjs';
import { ApplicationSelectionService } from '../_common/services/application-selection.service';
import { Application } from '../_common/models/application';
import { KeycloakService } from 'keycloak-angular';
import { environment } from '../../environments/environment';
import { SideMenuService } from '../_common/services/side-menu.service';

@Component({
  selector: 'app-portal-header',
  templateUrl: './portal-header.component.html'
})
export class PortalHeaderComponent implements OnInit, OnDestroy {
  portalApplications: any[] = [];

  displayName = '';

  navMenuSubscription: Subscription;

  @Output() public sidenavToggle = new EventEmitter();

  constructor(private menuService: MenuService,
    private applicationSelectionService: ApplicationSelectionService,
    private keycloak: KeycloakService,
    public sideMenuService: SideMenuService) {
  }

  ngOnInit() {
    this.navMenuSubscription = this.menuService.getPortalMenu()
      .subscribe((response) => {
        this.portalApplications = response;
      }
      );

      this.displayName = this.keycloak.getUsername().toLocaleUpperCase();
  }

  ngOnDestroy(): void {
    this.navMenuSubscription.unsubscribe();
  }
  logout() {
    this.keycloak.logout(environment.host + 'qa/portal/home');
  }
}
