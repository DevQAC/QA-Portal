import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CvHobbiesComponent } from './cv-hobbies.component';

describe('CvHobbiesComponent', () => {
  let component: CvHobbiesComponent;
  let fixture: ComponentFixture<CvHobbiesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CvHobbiesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CvHobbiesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
