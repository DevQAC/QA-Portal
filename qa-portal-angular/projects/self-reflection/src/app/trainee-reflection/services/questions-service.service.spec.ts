import { TestBed } from '@angular/core/testing';

import { QuestionsServiceService } from './questions-service.service';

describe('QuestionsServiceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: QuestionsServiceService = TestBed.get(QuestionsServiceService);
    expect(service).toBeTruthy();
  });
});
