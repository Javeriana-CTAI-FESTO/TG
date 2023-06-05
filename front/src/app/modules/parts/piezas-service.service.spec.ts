import { TestBed } from '@angular/core/testing';

import { PiezasServiceService } from './piezas-service.service';

describe('PiezasServiceService', () => {
  let service: PiezasServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PiezasServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
