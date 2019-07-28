import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { NoopAnimationsModule } from '@angular/platform-browser/animations';
import { MatPaginatorModule, MatSortModule, MatTableModule } from '@angular/material';

import { CohortTableComponent } from './cohort-table.component';

describe('CohortTableComponent', () => {
  let component: CohortTableComponent;
  let fixture: ComponentFixture<CohortTableComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CohortTableComponent ],
      imports: [
        NoopAnimationsModule,
        MatPaginatorModule,
        MatSortModule,
        MatTableModule,
      ]
    }).compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CohortTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should compile', () => {
    expect(component).toBeTruthy();
  });
});
