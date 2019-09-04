import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RoleChipsComponent } from './role-chips.component';

describe('RoleChipsComponent', () => {
  let component: RoleChipsComponent;
  let fixture: ComponentFixture<RoleChipsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RoleChipsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RoleChipsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
