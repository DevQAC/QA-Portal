import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CvQualisComponent } from './cv-qualis.component';

describe('CvQualisComponent', () => {
  let component: CvQualisComponent;
  let fixture: ComponentFixture<CvQualisComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CvQualisComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CvQualisComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
