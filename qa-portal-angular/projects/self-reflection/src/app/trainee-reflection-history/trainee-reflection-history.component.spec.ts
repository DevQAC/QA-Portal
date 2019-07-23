import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TraineeReflectionHistoryComponent } from './trainee-reflection-history.component';

describe('TraineeReflectionHistoryComponent', () => {
  let component: TraineeReflectionHistoryComponent;
  let fixture: ComponentFixture<TraineeReflectionHistoryComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TraineeReflectionHistoryComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TraineeReflectionHistoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
