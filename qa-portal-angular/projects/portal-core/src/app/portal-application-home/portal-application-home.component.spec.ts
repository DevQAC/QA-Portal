import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PortalApplicationHomeComponent } from './portal-application-home.component';

describe('PortalApplicationHomeComponent', () => {
  let component: PortalApplicationHomeComponent;
  let fixture: ComponentFixture<PortalApplicationHomeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PortalApplicationHomeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PortalApplicationHomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
