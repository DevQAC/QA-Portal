import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EditCourseDialogComponent } from './edit-course-dialog.component';

describe('EditCourseDialogComponent', () => {
  let component: EditCourseDialogComponent;
  let fixture: ComponentFixture<EditCourseDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditCourseDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditCourseDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
