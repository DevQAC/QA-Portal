import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WhyThisCourseQuestionComponent } from './why-this-course-question.component';

describe('WhyThisCourseQuestionComponent', () => {
  let component: WhyThisCourseQuestionComponent;
  let fixture: ComponentFixture<WhyThisCourseQuestionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ WhyThisCourseQuestionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WhyThisCourseQuestionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
