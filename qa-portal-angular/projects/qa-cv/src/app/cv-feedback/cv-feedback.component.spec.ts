import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CvFeedbackComponent } from './cv-feedback.component';

describe('CvFeedbackComponent', () => {
  let component: CvFeedbackComponent;
  let fixture: ComponentFixture<CvFeedbackComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CvFeedbackComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CvFeedbackComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
