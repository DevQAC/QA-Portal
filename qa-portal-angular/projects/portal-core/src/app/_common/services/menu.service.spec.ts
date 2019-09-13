import {MenuService} from './menu.service';
import {HttpClient} from '@angular/common/http';
import {TestBed} from '@angular/core/testing';
import {of} from 'rxjs';
import {PortalApplicationProjectsModel} from '../models/portal-application-projects.model';

describe('Menu Service tests', () => {
  let menuService: MenuService;

  let httpClientSpy: jasmine.SpyObj<HttpClient>;

  beforeEach(() => {
    const spy = jasmine.createSpyObj('HttpClient', ['get']);

    TestBed.configureTestingModule({
      providers: [
        MenuService,
        { provide: HttpClient, useValue: spy },
      ]
    });

    menuService = TestBed.get(MenuService);
    httpClientSpy = TestBed.get(HttpClient);
  });

  it('#getPortalMenu should return the stub Observable with the menu item', () => {
    // Set up pre conditions - http client to return 3 DepartmentApplications instances
    const stubMenusObservable = of(getMockMenuArray(3));
    httpClientSpy.get.and.returnValue(stubMenusObservable);

    // Invoke operation on the menu service
    const deptAppsObs = menuService.getPortalMenu();

    // Get response from the Observable returned by the http client mock (spy)
    let responseDeptApps = [];
    deptAppsObs.subscribe((response) => {
      responseDeptApps = response;
    });

    // Verify post conditions
    expect(httpClientSpy.get.calls.count()).toBe(1);
    expect(responseDeptApps.length).toBe(3);
  });

  function getMockMenuArray(numItems: number): PortalApplicationProjectsModel[] {
    const deptApps = [];
    for (let i = 0; i < numItems; i++) {
      deptApps.push(new PortalApplicationProjectsModel());
    }
    return deptApps;
  }
});

