import { TestBed } from '@angular/core/testing';

import { RatedQuestionsService } from './rated-questions.service';

describe('RatedQuestionsService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: RatedQuestionsService = TestBed.get(RatedQuestionsService);
    expect(service).toBeTruthy();
  });
});
