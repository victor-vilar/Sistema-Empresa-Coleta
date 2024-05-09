import { TestBed } from '@angular/core/testing';

import { CustomerAddressesDetailErrorsHelperService } from './customer-addresses-detail-errors-helper.service';

describe('CustomerAddressesDetailErrorsHelperService', () => {
  let service: CustomerAddressesDetailErrorsHelperService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CustomerAddressesDetailErrorsHelperService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
