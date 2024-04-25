import { TestBed } from '@angular/core/testing';

import { FormDetailHelperService } from './form-detail-helper.service';

describe('FormDetailHelperService', () => {
  let service: FormDetailHelperService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FormDetailHelperService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
