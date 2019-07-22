import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RatedQuestionComponent } from './rated-question.component';

describe('RatedQuestionComponent', () => {
  let component: RatedQuestionComponent;
  let fixture: ComponentFixture<RatedQuestionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RatedQuestionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RatedQuestionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
