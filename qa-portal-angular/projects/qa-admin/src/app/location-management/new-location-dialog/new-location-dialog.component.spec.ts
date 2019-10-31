import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NewLocationDialogComponent } from './new-location-dialog.component';

describe('NewLocationDialogComponent', () => {
  let component: NewLocationDialogComponent;
  let fixture: ComponentFixture<NewLocationDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NewLocationDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NewLocationDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
