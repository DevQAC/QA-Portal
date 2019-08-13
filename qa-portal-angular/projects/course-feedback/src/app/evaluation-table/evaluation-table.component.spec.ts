import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EvaluationTableComponent } from './evaluation-table.component';

describe('EvaluationTableComponent', () => {
  let component: EvaluationTableComponent;
  let fixture: ComponentFixture<EvaluationTableComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EvaluationTableComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EvaluationTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
