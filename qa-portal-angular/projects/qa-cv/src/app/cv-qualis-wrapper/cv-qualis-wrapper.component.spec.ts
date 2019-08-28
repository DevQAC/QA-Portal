import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CvQualisWrapperComponent } from './cv-qualis-wrapper.component';

describe('CvQualisWrapperComponent', () => {
  let component: CvQualisWrapperComponent;
  let fixture: ComponentFixture<CvQualisWrapperComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CvQualisWrapperComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CvQualisWrapperComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
