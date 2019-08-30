import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TrainerFeedbackHistoryComponent } from './trainer-feedback-history.component';

describe('TrainerFeedbackHistoryComponent', () => {
  let component: TrainerFeedbackHistoryComponent;
  let fixture: ComponentFixture<TrainerFeedbackHistoryComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TrainerFeedbackHistoryComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TrainerFeedbackHistoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
