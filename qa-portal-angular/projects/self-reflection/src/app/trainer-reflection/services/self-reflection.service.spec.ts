/* tslint:disable:no-unused-variable */

import { TestBed, async, inject } from '@angular/core/testing';
import { TrainerReflectionService } from './trainer-reflection.service';

describe('Service: SelfReflection', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [TrainerReflectionService]
    });
  });

  it('should ...', inject([TrainerReflectionService], (service: TrainerReflectionService) => {
    expect(service).toBeTruthy();
  }));
});
