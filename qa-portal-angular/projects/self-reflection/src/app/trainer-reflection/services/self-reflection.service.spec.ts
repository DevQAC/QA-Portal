/* tslint:disable:no-unused-variable */

import { TestBed, async, inject } from '@angular/core/testing';
import { SelfReflectionService } from './self-reflection.service';

describe('Service: SelfReflection', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [SelfReflectionService]
    });
  });

  it('should ...', inject([SelfReflectionService], (service: SelfReflectionService) => {
    expect(service).toBeTruthy();
  }));
});
