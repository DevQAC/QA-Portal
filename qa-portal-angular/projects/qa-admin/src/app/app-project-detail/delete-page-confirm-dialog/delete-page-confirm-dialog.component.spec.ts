import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DeletePageConfirmDialogComponent } from './delete-page-confirm-dialog.component';

describe('DeletePageConfirmDialogComponent', () => {
  let component: DeletePageConfirmDialogComponent;
  let fixture: ComponentFixture<DeletePageConfirmDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DeletePageConfirmDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DeletePageConfirmDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
