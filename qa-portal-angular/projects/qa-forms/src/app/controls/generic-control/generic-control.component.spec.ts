import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GenericControlComponent } from './generic-control.component';

describe('GenericControlComponent', () => {
  let component: GenericControlComponent;
  let fixture: ComponentFixture<GenericControlComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GenericControlComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GenericControlComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
