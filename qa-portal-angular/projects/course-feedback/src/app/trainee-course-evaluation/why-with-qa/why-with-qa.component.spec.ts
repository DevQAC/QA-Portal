import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WhyWithQAComponent } from './why-with-qa.component';

describe('WhyWithQAComponent', () => {
  let component: WhyWithQAComponent;
  let fixture: ComponentFixture<WhyWithQAComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ WhyWithQAComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WhyWithQAComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
