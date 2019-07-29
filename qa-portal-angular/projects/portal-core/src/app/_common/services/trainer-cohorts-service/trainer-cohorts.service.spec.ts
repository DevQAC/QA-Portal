import { TestBed } from '@angular/core/testing';

import { TrainerCohortsService } from './trainer-cohorts.service';

describe('TrainerCohortsService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: TrainerCohortsService = TestBed.get(TrainerCohortsService);
    expect(service).toBeTruthy();
  });
});
