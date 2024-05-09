import { TestBed } from '@angular/core/testing';

import { CustomerContractsDetailErrorsHelperService } from './customer-contracts-detail-errors-helper.service';

describe('CustomerContractsDetailErrorsHelperService', () => {
  let service: CustomerContractsDetailErrorsHelperService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CustomerContractsDetailErrorsHelperService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
