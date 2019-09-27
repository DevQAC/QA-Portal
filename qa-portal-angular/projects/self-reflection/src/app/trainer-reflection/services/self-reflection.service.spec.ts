/* tslint:disable:no-unused-variable */

import { TestBed, async, inject } from '@angular/core/testing';
import { DeprecatedTrainerReflectionService } from './deprecated-trainer-reflection.service';

describe('Service: SelfReflection', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [DeprecatedTrainerReflectionService]
    });
  });

  it('should ...', inject([DeprecatedTrainerReflectionService], (service: DeprecatedTrainerReflectionService) => {
    expect(service).toBeTruthy();
  }));
});
