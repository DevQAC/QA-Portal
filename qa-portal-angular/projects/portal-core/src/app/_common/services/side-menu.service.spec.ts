import { TestBed } from '@angular/core/testing';

import { SideMenuService } from './side-menu.service';

describe('SideMenuService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: SideMenuService = TestBed.get(SideMenuService);
    expect(service).toBeTruthy();
  });
});
