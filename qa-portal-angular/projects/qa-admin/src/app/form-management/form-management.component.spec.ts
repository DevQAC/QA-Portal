import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FormManagementComponent } from './form-management.component';

describe('FormManagementComponent', () => {
  let component: FormManagementComponent;
  let fixture: ComponentFixture<FormManagementComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FormManagementComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FormManagementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
