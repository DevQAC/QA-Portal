import { Component, OnDestroy, OnInit } from '@angular/core';
import { Event, NavigationStart, Router } from '@angular/router';
import { MenuService } from './_common/services/menu.service';
import { Subscription } from 'rxjs';
import { ApplicationSelectionService } from './_common/services/application-selection.service';
import { Application } from './_common/models/application';

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
        this.setSelectedDepartment(event.url);
      }
    });
  }

  ngOnInit() {
    this.errorApp = this.getErrorApplication();
    this.navMenuSubscription = this.menuService.getPortalMenu()
      .subscribe((response) => {
        this.portalApplications = response;
        this.setSelectedApplication(this.currentUrl);
        this.setSelectedDepartment(this.currentUrl);
      }
      );
  }

  ngOnDestroy(): void {
    this.navMenuSubscription.unsubscribe();
  }

  private setSelectedDepartment(currUrl: string): void {
    const currDep = this.getSelectedDepartmentForUrl(currUrl);
    if (!currDep) {
      this.launchLandingPage(currUrl);
    } else {
      this.applicationSelectionService.setSelectedDepartment(currDep);
    }
  }

  private getSelectedDepartmentForUrl(currUrl: string) {
    let dep = null;
    this.portalApplications.forEach((pa: any) => {
      if (pa.applications.some((app) => app.url.startsWith(currUrl))) {
        dep = pa;
      }
    });
    return dep;
  }

  private setSelectedApplication(currUrl: string): void {
    let selectedApp = this.getApplicationForUrl(currUrl);
    if (!selectedApp) {
      selectedApp = this.getSelectedApplicationForParameterizedUrl(currUrl);
    }

    if (!selectedApp) {
      this.launchLandingPage(currUrl);
    } else {
      this.applicationSelectionService.setSelectedApplication(selectedApp);
    }
  }

  private getApplicationForUrl(currUrl: string): Application {
    let selectedApp = null;
    this.portalApplications.forEach(pa => {
      pa.applications.forEach(a => {
        if (currUrl === a.url) {
          selectedApp = a;
        }
        a.menuItems.forEach(mi => {
          if (mi.url === currUrl) {
            selectedApp = a;
          }
        });
      });
    });
    return selectedApp;
  }

  private getSelectedApplicationForParameterizedUrl(currUrl: string): Application {
    // Substring to last index of '/' character
    const url = currUrl.substring(0, currUrl.lastIndexOf('/'));
    return this.getApplicationForUrl(url);
  }

  private launchLandingPage(currUrl: string): void {
    if (this.portalApplications.length > 0 &&
      !currUrl.startsWith('/qa/portal/error')) {
      this.applicationSelectionService.setSelectedApplication(this.portalApplications[0].applications[0]);
    } else if (currUrl.startsWith('/qa/portal/error')) {
      this.applicationSelectionService.setSelectedApplication(this.errorApp);
    }
  }

  private getErrorApplication(): Application {
    const errorApp = new Application();
    errorApp.url = '/qa/portal/error';
    return errorApp;
  }
}

