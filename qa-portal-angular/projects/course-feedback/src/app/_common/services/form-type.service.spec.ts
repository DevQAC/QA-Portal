import { TestBed } from '@angular/core/testing';

import { FormTypeService } from './form-type.service';

describe('FormTypeService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: FormTypeService = TestBed.get(FormTypeService);
    expect(service).toBeTruthy();
  });
});
