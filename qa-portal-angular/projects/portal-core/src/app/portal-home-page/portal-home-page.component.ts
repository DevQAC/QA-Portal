import { Component, OnInit, OnDestroy } from '@angular/core';
import { Application } from '../_common/models/application';
import { Subscription } from 'rxjs';
import { SideMenuService } from '../_common/services/side-menu.service';
import { ApplicationSelectionService } from '../_common/services/application-selection.service';

@Component({
  selector: 'app-portal-home-page',
  templateUrl: './portal-home-page.component.html',
  styleUrls: ['./portal-home-page.component.scss']
})
export class PortalHomePageComponent implements OnInit, OnDestroy {

  public header = 'Department Header';
  public description = 'Description';

  public applications: Application[];

  private depSubscription: Subscription;

  constructor(
    public sideMenuService: SideMenuService,
    public appSelService: ApplicationSelectionService
  ) {
  }

  ngOnInit() {
    this.header = 'Training';
    this.description = 'QA Training Department';
    this.applications = [
      {
        id: 7,
        name: 'Cohorts Ratings',
        url: '/qa/portal/training/admin/cohorts',
        menuItems: [{
          id: 5,
          name: 'Cohorts Ratings Summary',
          url: '/qa/portal/training/admin/cohorts',
          tooltip: 'All cohorts',
          level: null,
          displayOnMenu: true
        }, {
          id: 5,
          name: 'Cohorts Ratings Summary',
          url: '/qa/portal/training/admin/cohorts',
          tooltip: 'All cohorts',
          level: null,
          displayOnMenu: true
        }, {
          id: 5,
          name: 'Cohorts Ratings Summary',
          url: '/qa/portal/training/admin/cohorts',
          tooltip: 'All cohorts',
          level: null,
          displayOnMenu: true
        }, {
          id: 5,
          name: 'Cohorts Ratings Summary',
          url: '/qa/portal/training/admin/cohorts',
          tooltip: 'All cohorts',
          level: null,
          displayOnMenu: true
        }, {
          id: 5,
          name: 'Cohorts Ratings Summary',
          url: '/qa/portal/training/admin/cohorts',
          tooltip: 'All cohorts',
          level: null,
          displayOnMenu: true
        }]
      },{
        id: 7,
        name: 'Self Reflection',
        url: '/qa/portal/training/admin/cohorts',
        menuItems: [{
          id: 5,
          name: 'Cohorts Ratings Summary',
          url: '/qa/portal/training/admin/cohorts',
          tooltip: 'All cohorts',
          level: null,
          displayOnMenu: true
        }, {
          id: 5,
          name: 'Cohorts Ratings Summary',
          url: '/qa/portal/training/admin/cohorts',
          tooltip: 'All cohorts',
          level: null,
          displayOnMenu: true
        }]
      },{
        id: 7,
        name: 'CV Management',
        url: '/qa/portal/training/admin/cohorts',
        menuItems: [{
          id: 5,
          name: 'Search CVs',
          url: '/qa/portal/training/admin/cohorts',
          tooltip: 'All cohorts',
          level: null,
          displayOnMenu: true
        }, {
          id: 5,
          name: 'Cohorts Ratings Summary',
          url: '/qa/portal/training/admin/cohorts',
          tooltip: 'All cohorts',
          level: null,
          displayOnMenu: true
        }]
      }];
    // this.depSubscription = this.appSelService.getSelectedDepartment$().subscribe(dep => {
    //   this.header = dep.department.description;
    //   this.applications = dep.applications;
    //   this.applications = [
    //     {
    //       id: 7,
    //       name: 'Cohorts Ratings Summary',
    //       url: '/qa/portal/training/admin/cohorts',
    //       menuItems: [{
    //         id: 5,
    //         name: 'Cohorts Ratings Summary',
    //         url: '/qa/portal/training/admin/cohorts',
    //         tooltip: 'All cohorts',
    //         level: null,
    //         displayOnMenu: true
    //       }]
    //     }];
    // });
  }


  ngOnDestroy() {
    this.depSubscription.unsubscribe();
  }
}