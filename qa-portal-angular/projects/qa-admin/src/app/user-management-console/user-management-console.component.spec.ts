import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UserManagementConsoleComponent } from './user-management-console.component';

describe('UserManagementConsoleComponent', () => {
  let component: UserManagementConsoleComponent;
  let fixture: ComponentFixture<UserManagementConsoleComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UserManagementConsoleComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UserManagementConsoleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
