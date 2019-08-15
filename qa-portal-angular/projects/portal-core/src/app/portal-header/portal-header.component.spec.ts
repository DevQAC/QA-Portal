import {async, ComponentFixture, fakeAsync, TestBed, tick} from '@angular/core/testing';
import {MatIconModule, MatMenuModule, MatToolbarModule} from '@angular/material';
import {PortalHeaderComponent} from './portal-header.component';
import {MenuService} from '../_common/services/menu.service';
import {DepartmentApplications} from '../_common/models/department-applications';
import {KeycloakService} from 'keycloak-angular';
import {of} from 'rxjs';
import {Application} from '../_common/models/application';
import {Department} from '../_common/models/department';
import {CommonModule} from '@angular/common';
import {RouterTestingModule} from '@angular/router/testing';
import {Component} from '@angular/core';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {By} from '@angular/platform-browser';

@Component({
  template: ''
})
class DummyComponent {
}

describe('Portal Header Component Tests', () => {
  let component: PortalHeaderComponent;
  let fixture: ComponentFixture<PortalHeaderComponent>;
  let menuService: jasmine.SpyObj<MenuService>;
  let keycloakService: jasmine.SpyObj<KeycloakService>;

  beforeEach(async(() => {

  }));

  beforeEach(async(() => {

    // Create the spy objects for each of the dependencies injected into the component
    menuService = jasmine.createSpyObj('MenuService', ['getPortalMenu']);
    keycloakService = jasmine.createSpyObj('KeycloakService', ['logout']);

    // Set up the "function" spies set with the
    const getPortalMenuSpy = menuService.getPortalMenu.and.returnValue(of(createDeptApps()));

    // This is the equivalent of your NgModule setup. In declarations you only need the component you are testing. In providers,
    // specify the dependencies that need to be injected into your component and the spy object implementation of the dependency to inject
    // Also need to import the modules that are used by your angular html template
    TestBed.configureTestingModule({
      imports: [
        CommonModule,
        MatMenuModule,
        MatIconModule,
        MatToolbarModule,
        BrowserAnimationsModule,
        RouterTestingModule.withRoutes([
          {path: 'test/url', component: DummyComponent}
        ])
      ],
      declarations: [PortalHeaderComponent, DummyComponent],
      providers: [
        {provide: MenuService, useValue: menuService},
        {provide: KeycloakService, useValue: keycloakService},
      ]
    });

    // Get the services (that you've set up in the providers above), so you can check them in the tests
    menuService = TestBed.get(MenuService);
    keycloakService = TestBed.get(KeycloakService);

    // Creates component (including injecting the dependencies into the constructor). The fixture returned is a wrapper for the
    // the component and its HTML template
    fixture = TestBed.createComponent(PortalHeaderComponent);

    // Get the component from the fixture
    component = fixture.componentInstance;
  }));

  it('Should be presented with 1 department with its applications available to be shown on the navigation bar', () => {
    // Call detect changes, this will ensure ngOnInit() is completed
    fixture.detectChanges();

    // After ngOnInit is completed there should be 1 entry in the components portalApplications property
    expect(component.portalApplications.length).toBe(1);
  });

  it('Should have one Department called Test Dept', () => {
    // Call detect changes, this will ensure ngOnInit() is completed
    fixture.detectChanges();

    const dom: HTMLElement = fixture.nativeElement;
    const btn = dom.parentNode.querySelector('button');
    expect(btn.textContent.trim()).toBe('Test Dept');
  });

  it('Should have one application menu item called Test Appl after clicking the Test Dept button ', () => {
    // Call detect changes, this will ensure ngOnInit() is completed
    fixture.detectChanges();

    // The first anchor element should have text of 'Test Appl'
    const dom = fixture.debugElement;
    const btn = dom.query(By.css('button'));
    btn.triggerEventHandler('click', {});
    fixture.detectChanges();
    const anchorText = dom.query(By.css('a')).nativeElement.innerText;
    expect(anchorText.trim()).toBe('Test Appl');
  });


  it('Should have one application menu item with url of /test/url after clicking Test Dept button', () => {
    // Call detect changes, this will ensure ngOnInit() is completed
    fixture.detectChanges();

    // The first anchor href should have url of '/test/url'
    const dom = fixture.debugElement;
    const btn = dom.query(By.css('button'));
    btn.triggerEventHandler('click', {});
    fixture.detectChanges();
    const anchorUrl = dom.query(By.css('a')).nativeElement.href;
    expect(anchorUrl.endsWith('/test/url')).toBe(true);
  });

  function createDeptApps(): DepartmentApplications[] {
    const deptApps = new DepartmentApplications();
    deptApps.department = createMockDepartment();
    deptApps.applications = createMockApplications();
    return [deptApps];
  }

  function createMockDepartment(): Department {
    const dept = new Department();
    dept.name = 'Test Dept';
    dept.displayOrder = 1;
    return dept;
  }

  function createMockApplications(): Application[] {
    const appl = new Application();
    appl.url = '/test/url';
    appl.name = 'Test Appl';
    return [appl];
  }
});
