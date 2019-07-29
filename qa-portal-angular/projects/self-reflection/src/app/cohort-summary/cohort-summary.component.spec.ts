import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CohortSummaryComponent } from './cohort-summary.component';

describe('CohortSummaryComponent', () => {
  let component: CohortSummaryComponent;
  let fixture: ComponentFixture<CohortSummaryComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CohortSummaryComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CohortSummaryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
