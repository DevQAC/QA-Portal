import { APP_INITIALIZER, NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { PortalHeaderComponent } from './portal-header/portal-header.component';
import { QaCommonModule } from '../../../qa-common/src/app/app.module';
import { PortalSideMenuComponent } from './portal-side-menu/portal-side-menu.component';
import { PortalSideMenuContentComponent } from './portal-side-menu/portal-side-menu-content/portal-side-menu-content.component';
import { PortalFooterComponent } from './portal-footer/portal-footer.component';
import { KeycloakAngularModule, KeycloakService } from 'keycloak-angular';
import { QaErrorHandlerService } from './_common/services/qa-error-handler.service';
import { initializer } from '../../../../app-init';
import { QaToastrService } from './_common/services/qa-toastr.service';
import { HeaderLinkComponent } from './header-link/header-link.component';
import { MAT_DATE_LOCALE } from '@angular/material';
import { PortalApplicationHomeComponent } from './portal-application-home/portal-application-home.component';
import { ApplicationService } from './_common/services/application.service';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { PortalHomeComponent } from './portal-home/portal-home.component';

@NgModule({
  declarations: [
    AppComponent,
    PortalHeaderComponent,
    PortalSideMenuComponent,
    PortalSideMenuContentComponent,
    PortalFooterComponent,
    HeaderLinkComponent,
    PortalApplicationHomeComponent,
    PortalHomeComponent,
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    QaCommonModule,
    AppRoutingModule,
    HttpClientModule,
    KeycloakAngularModule
  ],
  providers: [
    ApplicationService,
    QaErrorHandlerService,
    QaToastrService,
    {
      provide: APP_INITIALIZER,
      useFactory: initializer,
      multi: true,
      deps: [KeycloakService]
    },
    {
      provide: MAT_DATE_LOCALE,
      useValue: 'en-GB'
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
