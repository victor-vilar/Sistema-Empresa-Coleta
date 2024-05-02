import { TestBed } from '@angular/core/testing';

import { CustomerContractsDetailItensErrorsHelperService } from './customer-contracts-detail-itens-errors-helper.service';

describe('CustomerContractsDetailItensErrorsHelperService', () => {
  let service: CustomerContractsDetailItensErrorsHelperService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CustomerContractsDetailItensErrorsHelperService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
