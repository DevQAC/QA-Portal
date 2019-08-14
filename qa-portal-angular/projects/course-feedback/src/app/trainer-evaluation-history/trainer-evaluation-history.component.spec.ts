import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TrainerEvaluationHistoryComponent } from './trainer-evaluation-history.component';

describe('TrainerEvaluationHistoryComponent', () => {
  let component: TrainerEvaluationHistoryComponent;
  let fixture: ComponentFixture<TrainerEvaluationHistoryComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TrainerEvaluationHistoryComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TrainerEvaluationHistoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
