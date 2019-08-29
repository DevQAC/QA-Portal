import { TestBed } from '@angular/core/testing';

import { ViewCvServiceService } from './view-cv-service.service';

describe('ViewCvServiceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ViewCvServiceService = TestBed.get(ViewCvServiceService);
    expect(service).toBeTruthy();
  });
});
