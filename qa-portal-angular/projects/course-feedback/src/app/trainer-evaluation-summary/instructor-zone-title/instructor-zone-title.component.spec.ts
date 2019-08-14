import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { InstructorZoneTitleComponent } from './instructor-zone-title.component';

describe('InstructorZoneTitleComponent', () => {
  let component: InstructorZoneTitleComponent;
  let fixture: ComponentFixture<InstructorZoneTitleComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ InstructorZoneTitleComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(InstructorZoneTitleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
