import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CohortTraineesComponent } from './cohort-trainees.component';

describe('CohortTraineesComponent', () => {
  let component: CohortTraineesComponent;
  let fixture: ComponentFixture<CohortTraineesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CohortTraineesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CohortTraineesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
