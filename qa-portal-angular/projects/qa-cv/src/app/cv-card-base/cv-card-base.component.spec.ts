import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CvCardBaseComponent } from './cv-card-base.component';

describe('CvCardBaseComponent', () => {
  let component: CvCardBaseComponent;
  let fixture: ComponentFixture<CvCardBaseComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CvCardBaseComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CvCardBaseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
