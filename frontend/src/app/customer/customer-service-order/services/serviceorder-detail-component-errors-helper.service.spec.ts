import { TestBed } from '@angular/core/testing';

import { ServiceorderDetailComponentErrorsHelperService } from './serviceorder-detail-component-errors-helper.service';

describe('ServiceorderDetailComponentErrorsHelperService', () => {
  let service: ServiceorderDetailComponentErrorsHelperService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ServiceorderDetailComponentErrorsHelperService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
