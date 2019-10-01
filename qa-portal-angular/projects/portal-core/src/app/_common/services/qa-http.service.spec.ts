import { TestBed } from '@angular/core/testing';

import { QaHttpService } from './qa-http.service';

describe('QaHttpService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: QaHttpService = TestBed.get(QaHttpService);
    expect(service).toBeTruthy();
  });
});
