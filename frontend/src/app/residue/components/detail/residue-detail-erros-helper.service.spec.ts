import { TestBed } from '@angular/core/testing';

import { ResidueDetailErrosHelperService } from '../../services/residue-detail-erros-helper.service';

describe('ResidueDetailErrosHelperService', () => {
  let service: ResidueDetailErrosHelperService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ResidueDetailErrosHelperService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
