import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CvProfileComponent } from './cv-profile.component';

describe('CvProfileComponent', () => {
  let component: CvProfileComponent;
  let fixture: ComponentFixture<CvProfileComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CvProfileComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CvProfileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
