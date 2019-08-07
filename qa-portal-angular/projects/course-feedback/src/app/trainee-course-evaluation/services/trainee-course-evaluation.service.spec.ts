import { TestBed } from '@angular/core/testing';

import { TraineeCourseEvaluationService } from './trainee-course-evaluation.service';

describe('TraineeCourseEvaluationService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: TraineeCourseEvaluationService = TestBed.get(TraineeCourseEvaluationService);
    expect(service).toBeTruthy();
  });
});
