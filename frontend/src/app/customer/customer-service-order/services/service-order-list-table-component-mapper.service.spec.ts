import { TestBed } from '@angular/core/testing';

import { ServiceOrderListTableComponentMapperService } from './service-order-list-table-component-mapper.service';

describe('ServiceOrderListTableComponentMapperService', () => {
  let service: ServiceOrderListTableComponentMapperService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ServiceOrderListTableComponentMapperService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
