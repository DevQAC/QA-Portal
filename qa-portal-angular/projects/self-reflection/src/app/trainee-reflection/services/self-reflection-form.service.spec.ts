import { TestBed } from '@angular/core/testing';

import { SelfReflectionFormService } from './self-reflection-form.service';

describe('SelfReflectionFormService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: SelfReflectionFormService = TestBed.get(SelfReflectionFormService);
    expect(service).toBeTruthy();
  });
});
