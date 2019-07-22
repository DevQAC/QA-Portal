import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PortalSideMenuContentComponent } from './portal-side-menu-content.component';

describe('PortalSideMenuContentComponent', () => {
  let component: PortalSideMenuContentComponent;
  let fixture: ComponentFixture<PortalSideMenuContentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PortalSideMenuContentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PortalSideMenuContentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
