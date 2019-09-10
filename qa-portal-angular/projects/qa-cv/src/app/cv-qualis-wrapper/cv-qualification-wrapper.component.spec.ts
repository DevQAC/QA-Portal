import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CvQualificationWrapperComponent } from './cv-qualification-wrapper.component';

describe('CvQualisWrapperComponent', () => {
  let component: CvQualificationWrapperComponent;
  let fixture: ComponentFixture<CvQualificationWrapperComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CvQualificationWrapperComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CvQualificationWrapperComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
