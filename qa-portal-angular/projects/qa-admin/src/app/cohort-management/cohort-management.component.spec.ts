import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CohortManagementComponent } from './cohort-management.component';

describe('CohortManagementComponent', () => {
  let component: CohortManagementComponent;
  let fixture: ComponentFixture<CohortManagementComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CohortManagementComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CohortManagementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
