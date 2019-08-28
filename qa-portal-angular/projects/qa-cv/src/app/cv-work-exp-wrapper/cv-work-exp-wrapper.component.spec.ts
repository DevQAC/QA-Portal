import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CvWorkExpWrapperComponent } from './cv-work-exp-wrapper.component';

describe('CvWorkExpWrapperComponent', () => {
  let component: CvWorkExpWrapperComponent;
  let fixture: ComponentFixture<CvWorkExpWrapperComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CvWorkExpWrapperComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CvWorkExpWrapperComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
