import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AppProjectDetailComponent } from './app-project-detail.component';

describe('AppProjectDetailComponent', () => {
  let component: AppProjectDetailComponent;
  let fixture: ComponentFixture<AppProjectDetailComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AppProjectDetailComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AppProjectDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
