import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CvSkillsComponent } from './cv-skills.component';

describe('CvSkillsComponent', () => {
  let component: CvSkillsComponent;
  let fixture: ComponentFixture<CvSkillsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CvSkillsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CvSkillsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
