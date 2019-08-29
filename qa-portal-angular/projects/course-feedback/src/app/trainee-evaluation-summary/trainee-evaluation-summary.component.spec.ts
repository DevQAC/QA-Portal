import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TraineeEvaluationSummaryComponent } from './trainee-evaluation-summary.component';

describe('TraineeEvaluationSummaryComponent', () => {
  let component: TraineeEvaluationSummaryComponent;
  let fixture: ComponentFixture<TraineeEvaluationSummaryComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TraineeEvaluationSummaryComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TraineeEvaluationSummaryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
