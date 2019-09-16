import {Component, OnDestroy, OnInit} from '@angular/core';
import {Event, NavigationStart, Router} from '@angular/router';
import {MenuService} from './_common/services/menu.service';
import {Subscription} from 'rxjs';
import {ApplicationSelectionService} from './_common/services/application-selection.service';
import {PortalProjectModel} from './_common/models/portal-project.model';
import {PortalApplicationProjectsModel} from './_common/models/portal-application-projects.model';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit, OnDestroy {
  portalApplicationProjectsList: PortalApplicationProjectsModel[] = [];

  errorApp: PortalProjectModel;

  currentUrl: string;

  navMenuSubscription: Subscription;

  constructor(private menuService: MenuService,
              private router: Router,
              private applicationSelectionService: ApplicationSelectionService) {
    router.events.subscribe((event: Event) => {
      if (event instanceof NavigationStart) {
        this.currentUrl = event.url;
        this.setSelectedApplication(event.url);
        this.setSelectedProject(event.url);
      }
    });
  }

  ngOnInit() {
    this.errorApp = this.getErrorApplication();
    this.navMenuSubscription = this.menuService.getPortalMenu()
      .subscribe((response) => {
          this.portalApplicationProjectsList = response;
          console.log('Got Portal applications from service');
          this.setSelectedApplication(this.currentUrl);
          this.setSelectedProject(this.currentUrl);
        }
      );
  }

  ngOnDestroy(): void {
    this.navMenuSubscription.unsubscribe();
  }

  private setSelectedApplication(currUrl: string): void {
    const currApp = this.getSelectedApplicationForUrl(currUrl);
    if (!currApp) {
      this.launchLandingPage(currUrl);
    } else {
      this.applicationSelectionService.setSelectedApplication(currApp);
    }
  }

  private getSelectedApplicationForUrl(currUrl: string): PortalApplicationProjectsModel {
    return this.portalApplicationProjectsList
              .find((pa: PortalApplicationProjectsModel) => currUrl.startsWith(pa.portalApplication.baseUrl));
  }

  private setSelectedProject(currUrl: string): void {
    let selectedProject = this.getProjectForUrl(currUrl);
    if (!selectedProject) {
      selectedProject = this.getSelectedProjectForParameterizedUrl(currUrl);
    }

    if (!selectedProject) {
      this.launchLandingPage(currUrl);
    } else {
      this.applicationSelectionService.setSelectedProject(selectedProject);
    }
  }

  private getProjectForUrl(currUrl: string): PortalProjectModel {
    let selectedApp = null;
    this.portalApplicationProjectsList.forEach(pa => {
      pa.portalProjects.forEach(pp => {
        if (currUrl === pp.url) {
          selectedApp = pp;
        }
        pp.projectPages.forEach(page => {
          if (page.url === currUrl) {
            selectedApp = pp;
          }
        });
      });
    });
    return selectedApp;
  }

  private getSelectedProjectForParameterizedUrl(currUrl: string): PortalProjectModel {
    // Substring to last index of '/' character
    const url = currUrl.substring(0, currUrl.lastIndexOf('/'));
    return this.getProjectForUrl(url);
  }

  private launchLandingPage(currUrl: string): void {
    if (this.portalApplicationProjectsList.length > 0 &&
      !currUrl.startsWith('/qa/portal/error')) {
      this.applicationSelectionService.setSelectedProject(this.portalApplicationProjectsList[0].portalProjects[0]);
    } else if (currUrl.startsWith('/qa/portal/error')) {
      this.applicationSelectionService.setSelectedProject(this.errorApp);
    }
  }

  private getErrorApplication(): PortalProjectModel {
    const errorApp = new PortalProjectModel();
    errorApp.url = '/qa/portal/error';
    return errorApp;
  }
}

