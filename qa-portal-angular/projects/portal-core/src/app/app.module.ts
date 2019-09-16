import { APP_INITIALIZER, NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { PortalHeaderComponent } from './portal-header/portal-header.component';
import { QaCommonModule } from '../../../qa-common/src/app/app.module';
import { PortalSideMenuComponent } from './portal-side-menu/portal-side-menu.component';
import { PortalSideMenuContentComponent } from './portal-side-menu/portal-side-menu-content/portal-side-menu-content.component';
import { PortalFooterComponent } from './portal-footer/portal-footer.component';
import { PortalHomeSharedModule } from '../../../portal-home/src/app/app.module';
import { KeycloakAngularModule, KeycloakService } from 'keycloak-angular';
import { QaErrorHandlerService } from './_common/services/qa-error-handler.service';
import { QaErrorSharedModule } from '../../../qa-error-app/src/app/app.module';
import { CourseFeedbackSharedModule } from '../../../course-feedback/src/app/app.module';
import { SelfReflectionSharedModule } from '../../../self-reflection/src/app/app.module';
import { initializer } from '../../../../app-init';
import { QaToastrService } from './_common/services/qa-toastr.service';
import { QaCvModule } from 'projects/qa-cv/src/app/qa-cv.module';
import { HeaderLinkComponent } from './header-link/header-link.component';
import { MAT_DATE_LOCALE } from '@angular/material';
import { PortalHomePageComponent } from './portal-home-page/portal-home-page.component';
import { ApplicationService } from './_common/services/application.service';

@NgModule({
  declarations: [
    AppComponent,
    PortalHeaderComponent,
    PortalSideMenuComponent,
    PortalSideMenuContentComponent,
    PortalFooterComponent,
    HeaderLinkComponent,
    PortalHomePageComponent,
  ],
  imports: [
    QaCommonModule,
    AppRoutingModule,
    PortalHomeSharedModule.forRoot(),
    QaErrorSharedModule.forRoot(),
    SelfReflectionSharedModule.forRoot(),
    CourseFeedbackSharedModule.forRoot(),
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
