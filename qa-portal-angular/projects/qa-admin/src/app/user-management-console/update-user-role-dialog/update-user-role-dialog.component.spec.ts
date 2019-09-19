import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateUserRoleDialogComponent } from './update-user-role-dialog.component';

describe('UpdateUserRoleDialogComponent', () => {
  let component: UpdateUserRoleDialogComponent;
  let fixture: ComponentFixture<UpdateUserRoleDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UpdateUserRoleDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdateUserRoleDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
