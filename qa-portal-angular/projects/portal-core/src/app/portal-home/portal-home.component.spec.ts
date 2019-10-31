import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PortalHomeComponent } from './portal-home.component';

describe('PortalHomeComponent', () => {
  let component: PortalHomeComponent;
  let fixture: ComponentFixture<PortalHomeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PortalHomeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PortalHomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
