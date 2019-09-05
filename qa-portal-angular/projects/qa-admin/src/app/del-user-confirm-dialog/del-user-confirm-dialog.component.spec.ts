import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DelUserConfirmDialogComponent } from './del-user-confirm-dialog.component';

describe('DelUserConfirmDialogComponent', () => {
  let component: DelUserConfirmDialogComponent;
  let fixture: ComponentFixture<DelUserConfirmDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DelUserConfirmDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DelUserConfirmDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
