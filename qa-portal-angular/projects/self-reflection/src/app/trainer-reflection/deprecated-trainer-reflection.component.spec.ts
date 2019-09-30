/* tslint:disable:no-unused-variable */
import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { By } from '@angular/platform-browser';
import { DebugElement } from '@angular/core';

import { DeprecatedTrainerReflectionComponent } from './deprecated-trainer-reflection.component';

describe('TrainerReflectionComponent', () => {
  let component: DeprecatedTrainerReflectionComponent;
  let fixture: ComponentFixture<DeprecatedTrainerReflectionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DeprecatedTrainerReflectionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DeprecatedTrainerReflectionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
