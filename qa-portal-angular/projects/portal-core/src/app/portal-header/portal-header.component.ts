import { Component, OnInit } from '@angular/core';
import { KeycloakService } from 'keycloak-angular';
import { environment } from '../../environments/environment';
import { ApplicationService } from '../_common/services/application.service';

@Component({
  selector: 'app-portal-header',
  templateUrl: './portal-header.component.html'
})
export class PortalHeaderComponent implements OnInit{
  public displayName = '';

  constructor(
    public appService: ApplicationService,
    private keycloak: KeycloakService) { }

  ngOnInit() {
    this.displayName = this.keycloak.getUsername().toLocaleUpperCase();
  }

  logout() {
    this.keycloak.logout(environment.host + 'qa/portal/home');
  }
}
