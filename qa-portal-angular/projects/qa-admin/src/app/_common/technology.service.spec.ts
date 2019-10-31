import { TestBed } from '@angular/core/testing';

import { TechnologyService } from './technology.service';

describe('TechnologyService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: TechnologyService = TestBed.get(TechnologyService);
    expect(service).toBeTruthy();
  });
});
