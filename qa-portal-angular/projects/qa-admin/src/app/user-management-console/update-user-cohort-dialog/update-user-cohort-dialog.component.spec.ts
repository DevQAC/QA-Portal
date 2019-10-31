import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateUserCohortDialogComponent } from './update-user-cohort-dialog.component';

describe('UpdateUserCohortDialogComponent', () => {
  let component: UpdateUserCohortDialogComponent;
  let fixture: ComponentFixture<UpdateUserCohortDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UpdateUserCohortDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdateUserCohortDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
