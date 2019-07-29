import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TrainerCohortsComponent } from './trainer-cohorts.component';

describe('TrainerCohortsComponent', () => {
  let component: TrainerCohortsComponent;
  let fixture: ComponentFixture<TrainerCohortsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TrainerCohortsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TrainerCohortsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
