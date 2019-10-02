import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NewCategoryDialogComponent } from './new-category-dialog.component';

describe('NewCategoryDialogComponent', () => {
  let component: NewCategoryDialogComponent;
  let fixture: ComponentFixture<NewCategoryDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NewCategoryDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NewCategoryDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
