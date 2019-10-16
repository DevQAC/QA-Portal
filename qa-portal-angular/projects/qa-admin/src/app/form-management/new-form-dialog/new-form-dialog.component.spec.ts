import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NewFormDialogComponent } from './new-form-dialog.component';

describe('NewFormDialogComponent', () => {
  let component: NewFormDialogComponent;
  let fixture: ComponentFixture<NewFormDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NewFormDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NewFormDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
