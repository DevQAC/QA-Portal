import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CourseEvaluationComponent } from './course-evaluation.component';

describe('CourseEvaluationComponent', () => {
  let component: CourseEvaluationComponent;
  let fixture: ComponentFixture<CourseEvaluationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CourseEvaluationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CourseEvaluationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
