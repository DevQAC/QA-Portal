/* tslint:disable:no-unused-variable */

import { TestBed, async, inject } from '@angular/core/testing';
import { TraineeReflectionService } from './trainee-reflection.service';

describe('Service: TraineeReflection', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [TraineeReflectionService]
    });
  });

  it('should ...', inject([TraineeReflectionService], (service: TraineeReflectionService) => {
    expect(service).toBeTruthy();
  }));
});
