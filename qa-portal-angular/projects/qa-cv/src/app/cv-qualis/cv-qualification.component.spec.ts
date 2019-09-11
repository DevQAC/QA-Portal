import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CvQualificationComponent } from './cv-qualification.component';

describe('CvQualisComponent', () => {
  let component: CvQualificationComponent;
  let fixture: ComponentFixture<CvQualificationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CvQualificationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CvQualificationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
