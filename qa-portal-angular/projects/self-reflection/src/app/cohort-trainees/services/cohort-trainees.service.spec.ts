/* tslint:disable:no-unused-variable */

import { TestBed, async, inject } from '@angular/core/testing';
import { CohortTraineesService } from './cohort-trainees.service';

describe('Service: SelfReflection', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [CohortTraineesService]
    });
  });

  it('should ...', inject([CohortTraineesService], (service: CohortTraineesService) => {
    expect(service).toBeTruthy();
  }));
});
