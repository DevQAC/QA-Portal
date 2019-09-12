import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PortalHomePageComponent } from './portal-home-page.component';

describe('PortalHomePageComponent', () => {
  let component: PortalHomePageComponent;
  let fixture: ComponentFixture<PortalHomePageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PortalHomePageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PortalHomePageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
