import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PortalSideMenuComponent } from './portal-side-menu.component';

describe('PortalSideMenuComponent', () => {
  let component: PortalSideMenuComponent;
  let fixture: ComponentFixture<PortalSideMenuComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PortalSideMenuComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PortalSideMenuComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
