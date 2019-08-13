import { TestBed } from '@angular/core/testing';

import { RetrieveTrainerEvaluationHistoryService } from './retrieve-trainer-evaluation-history.service';

describe('RetrieveTrainerEvaluationHistoryService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: RetrieveTrainerEvaluationHistoryService = TestBed.get(RetrieveTrainerEvaluationHistoryService);
    expect(service).toBeTruthy();
  });
});
