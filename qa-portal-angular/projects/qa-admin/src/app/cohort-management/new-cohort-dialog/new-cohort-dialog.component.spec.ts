import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NewCohortDialogComponent } from './new-cohort-dialog.component';

describe('NewCohortDialogComponent', () => {
  let component: NewCohortDialogComponent;
  let fixture: ComponentFixture<NewCohortDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NewCohortDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NewCohortDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
