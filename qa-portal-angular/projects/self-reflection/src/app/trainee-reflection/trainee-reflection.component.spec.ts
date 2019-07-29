import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TraineeReflectionComponent } from './trainee-reflection.component';

describe('TraineeReflectionComponent', () => {
  let component: TraineeReflectionComponent;
  let fixture: ComponentFixture<TraineeReflectionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TraineeReflectionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TraineeReflectionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
