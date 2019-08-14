import { TestBed } from '@angular/core/testing';

import { TrainerEvaluationServicesService } from './trainer-evaluation-services.service';

describe('TrainerEvaluationServicesService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: TrainerEvaluationServicesService = TestBed.get(TrainerEvaluationServicesService);
    expect(service).toBeTruthy();
  });
});
