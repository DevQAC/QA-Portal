import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { QaErrorPageComponent } from './qa-error-page.component';

describe('QaErrorPageComponent', () => {
  let component: QaErrorPageComponent;
  let fixture: ComponentFixture<QaErrorPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ QaErrorPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(QaErrorPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
