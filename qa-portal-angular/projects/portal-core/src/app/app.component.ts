import {Component, OnDestroy, OnInit} from '@angular/core';
import {Event, NavigationStart, Router} from '@angular/router';
import {MenuService} from './_common/services/menu-service';
import {Subscription} from 'rxjs';
import {ApplicationSelectionService} from './_common/services/application-selection.service';
import {Application} from './_common/models/application';
import {MenuItem} from './_common/models/menu-item';
import {first} from 'rxjs/operators';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit, OnDestroy {
  portalApplications: any[] = [];

  errorApp: Application;

  currentUrl: string;

  navMenuSubscription: Subscription;

  constructor(private menuService: MenuService,
              private router: Router,
              private applicationSelectionService: ApplicationSelectionService) {
    router.events.subscribe((event: Event) => {
      if (event instanceof NavigationStart) {
        this.currentUrl = event.url;
        this.setSelectedApplication(event.url);
      }
    });
  }

  ngOnInit() {
    this.errorApp = this.getErrorApplication();

    this.navMenuSubscription = this.menuService.getPortalMenu()
      .subscribe((response) => {
          this.portalApplications = response;
          this.setSelectedApplication(this.currentUrl);
        }
      );
  }

  ngOnDestroy(): void {
    this.navMenuSubscription.unsubscribe();
  }

  private setSelectedApplication(currUrl: string): void {
    let appSelected = false;
    this.portalApplications.forEach((pa) => {
      pa.applications.forEach(a => {
        if (currUrl.startsWith(this.getAppBaseUrl(a.url)) &&
          a.url !== '/qa/portal' &&
          !appSelected) {
          this.applicationSelectionService.setSelectedApplication(a);
          console.log('app selected ' + a.url);
          appSelected = true;
        }
      });
    });

    // If no application selected and we've completed the loading of the applications as part of application startup
    // determine whether to navigate to home page or error page
    if (!appSelected &&
      this.portalApplications.length > 0 &&
      !currUrl.startsWith('/qa/portal/error')) {
      this.applicationSelectionService.setSelectedApplication(this.portalApplications[0].applications[0]);
    } else if (currUrl.startsWith('/qa/portal/error')) {
      this.applicationSelectionService.setSelectedApplication(this.errorApp);
    }
  }

  private getAppBaseUrl(appUrl: string): string {
    // Get index of 3rd / by substring of /qa/portal/
    let appString = '/qa/portal/home';

    const mainUrl = appUrl.substring('/qa/portal/'.length);
    console.log('Main url ' + mainUrl);

    const firstSeparator = mainUrl.indexOf('/');
    console.log('First separator index ' + firstSeparator);

    if (firstSeparator > -1) {
      appString = appUrl.substring(0, '/qa/portal/'.length + firstSeparator);
    }
    console.log('App String ' + appString);
    return appString;
  }

  private getErrorApplication(): Application {
    const errorApp = new Application();
    errorApp.url = '/qa/portal/error';
    return errorApp;
  }
}

