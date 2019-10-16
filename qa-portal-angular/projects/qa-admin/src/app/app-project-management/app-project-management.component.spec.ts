import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AppProjectManagementComponent } from './app-project-management.component';

describe('AppProjectManagementComponent', () => {
  let component: AppProjectManagementComponent;
  let fixture: ComponentFixture<AppProjectManagementComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AppProjectManagementComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AppProjectManagementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
