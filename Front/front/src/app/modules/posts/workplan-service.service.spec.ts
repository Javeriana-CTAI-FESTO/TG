import { TestBed } from '@angular/core/testing';

import { WorkplanServiceService } from './workplan-service.service';

describe('WorkplanServiceService', () => {
  let service: WorkplanServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(WorkplanServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
