import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SelfReflectionHistoryComponent } from './Self-reflection-history.component';

describe('SelfReflectionHistoryComponent', () => {
  let component: SelfReflectionHistoryComponent;
  let fixture: ComponentFixture<SelfReflectionHistoryComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SelfReflectionHistoryComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SelfReflectionHistoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
