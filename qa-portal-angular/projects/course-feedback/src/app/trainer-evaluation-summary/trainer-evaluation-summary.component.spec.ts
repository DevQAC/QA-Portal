import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TrainerEvaluationSummaryComponent } from './trainer-evaluation-summary.component';

describe('TrainerEvaluationSummaryComponent', () => {
  let component: TrainerEvaluationSummaryComponent;
  let fixture: ComponentFixture<TrainerEvaluationSummaryComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TrainerEvaluationSummaryComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TrainerEvaluationSummaryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
