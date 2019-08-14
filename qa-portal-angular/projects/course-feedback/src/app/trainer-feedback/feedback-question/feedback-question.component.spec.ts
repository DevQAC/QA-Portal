import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FeedbackQuestionComponent } from './feedback-question.component';

describe('FeedbackQuestionComponent', () => {
  let component: FeedbackQuestionComponent;
  let fixture: ComponentFixture<FeedbackQuestionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FeedbackQuestionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FeedbackQuestionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
