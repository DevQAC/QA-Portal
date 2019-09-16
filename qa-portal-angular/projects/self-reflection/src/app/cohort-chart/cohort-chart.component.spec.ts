import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CohortChartComponent } from './cohort-chart.component';

describe('CohortChartComponent', () => {
  let component: CohortChartComponent;
  let fixture: ComponentFixture<CohortChartComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CohortChartComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CohortChartComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
