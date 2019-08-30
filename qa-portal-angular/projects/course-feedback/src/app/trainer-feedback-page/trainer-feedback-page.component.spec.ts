import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TrainerFeedbackPageComponent } from './trainer-feedback-page.component';

describe('TrainerFeedbackPageComponent', () => {
  let component: TrainerFeedbackPageComponent;
  let fixture: ComponentFixture<TrainerFeedbackPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TrainerFeedbackPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TrainerFeedbackPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
