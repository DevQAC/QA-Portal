import { TestBed } from '@angular/core/testing';

import { CohortService } from './cohort.service';

describe('CohortService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: CohortService = TestBed.get(CohortService);
    expect(service).toBeTruthy();
  });
});
