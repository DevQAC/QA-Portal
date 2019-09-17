import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DeleteCohortDialogComponent } from './delete-cohort-dialog.component';

describe('DeleteCohortDialogComponent', () => {
  let component: DeleteCohortDialogComponent;
  let fixture: ComponentFixture<DeleteCohortDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DeleteCohortDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DeleteCohortDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
