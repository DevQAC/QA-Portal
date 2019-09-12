import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchBarBackdropComponent } from './search-bar-backdrop.component';

describe('SearchBarBackdropComponent', () => {
  let component: SearchBarBackdropComponent;
  let fixture: ComponentFixture<SearchBarBackdropComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SearchBarBackdropComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchBarBackdropComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
