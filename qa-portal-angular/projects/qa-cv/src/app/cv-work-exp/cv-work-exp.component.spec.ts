import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CvWorkExpComponent } from './cv-work-exp.component';

describe('CvWorkExpComponent', () => {
  let component: CvWorkExpComponent;
  let fixture: ComponentFixture<CvWorkExpComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CvWorkExpComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CvWorkExpComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
