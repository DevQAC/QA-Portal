import {Component, OnDestroy, OnInit} from '@angular/core';
import {Event, NavigationStart, Router} from '@angular/router';
import {MenuService} from './_common/services/menu-service';
import {Subscription} from 'rxjs';
import {ApplicationSelectionService} from './_common/services/application-selection.service';
import {Application} from './_common/models/application';
import {MenuItem} from './_common/models/menu-item';

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
        if ((currUrl.startsWith(a.url) || this.isAppMenuItem(currUrl, a.menuItems)) &&
          a.url !== '/qa/portal' &&
          !appSelected) {
          this.applicationSelectionService.setSelectedApplication(a);
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

  private isAppMenuItem(url: string, menuItems: MenuItem[]): boolean {
    return menuItems.filter(mi => url.startsWith(mi.url)).length > 0;
  }

  private getErrorApplication(): Application {
    const errorApp = new Application();
    errorApp.url = '/qa/portal/error';
    return errorApp;
  }
}

