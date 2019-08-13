import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProblemReporterComponent } from './problem-reporter.component';

describe('ProblemReporterComponent', () => {
  let component: ProblemReporterComponent;
  let fixture: ComponentFixture<ProblemReporterComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProblemReporterComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProblemReporterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
