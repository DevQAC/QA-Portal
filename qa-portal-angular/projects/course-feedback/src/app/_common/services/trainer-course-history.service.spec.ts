import { TestBed } from '@angular/core/testing';

import { TrainerCourseHistoryService } from './trainer-course-history.service';

describe('TrainerEvaluationHistoryService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: TrainerCourseHistoryService = TestBed.get(TrainerCourseHistoryService);
    expect(service).toBeTruthy();
  });
});
