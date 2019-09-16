import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CohortChipsComponent } from './cohort-chips.component';

describe('CohortChipsComponent', () => {
  let component: CohortChipsComponent;
  let fixture: ComponentFixture<CohortChipsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CohortChipsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CohortChipsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
