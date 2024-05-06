import { TestBed } from '@angular/core/testing';

import { EquipmentDetailErrorsHelperService } from './equipment-detail-errors-helper.service';

describe('EquipmentDetailErrorsHelperService', () => {
  let service: EquipmentDetailErrorsHelperService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(EquipmentDetailErrorsHelperService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
