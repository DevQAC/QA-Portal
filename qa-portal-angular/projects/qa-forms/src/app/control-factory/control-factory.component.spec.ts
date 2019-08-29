import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ControlFactoryComponent } from './control-factory.component';

describe('ControlFactoryComponent', () => {
  let component: ControlFactoryComponent;
  let fixture: ComponentFixture<ControlFactoryComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ControlFactoryComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ControlFactoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
