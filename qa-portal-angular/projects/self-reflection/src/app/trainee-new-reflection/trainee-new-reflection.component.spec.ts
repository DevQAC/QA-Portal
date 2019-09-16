import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TraineeNewReflectionComponent } from './trainee-new-reflection.component';

describe('TraineeNewReflectionComponent', () => {
  let component: TraineeNewReflectionComponent;
  let fixture: ComponentFixture<TraineeNewReflectionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TraineeNewReflectionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TraineeNewReflectionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
