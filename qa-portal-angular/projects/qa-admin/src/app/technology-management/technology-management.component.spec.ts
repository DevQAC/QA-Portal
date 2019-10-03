import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TechnologyManagementComponent } from './technology-management.component';

describe('TechnologyManagementComponent', () => {
  let component: TechnologyManagementComponent;
  let fixture: ComponentFixture<TechnologyManagementComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TechnologyManagementComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TechnologyManagementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
