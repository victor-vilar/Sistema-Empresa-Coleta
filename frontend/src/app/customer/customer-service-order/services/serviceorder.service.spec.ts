import { TestBed } from '@angular/core/testing';

import { ServiceorderService } from './serviceorder.service';

describe('ServiceorderService', () => {
  let service: ServiceorderService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ServiceorderService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
