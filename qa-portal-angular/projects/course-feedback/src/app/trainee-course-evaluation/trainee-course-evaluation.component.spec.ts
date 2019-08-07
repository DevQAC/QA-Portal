import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TraineeCourseEvaluationComponent } from './trainee-course-evaluation.component';

describe('TraineeCourseEvaluationComponent', () => {
  let component: TraineeCourseEvaluationComponent;
  let fixture: ComponentFixture<TraineeCourseEvaluationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TraineeCourseEvaluationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TraineeCourseEvaluationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
