import { TestBed } from '@angular/core/testing';

import { ContractsListComponentMapperService } from './contracts-list-component-mapper.service';

describe('ContractsListComponentMapperService', () => {
  let service: ContractsListComponentMapperService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ContractsListComponentMapperService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
