import { TestBed } from '@angular/core/testing';

import { PdfBuilderService } from './pdf-builder.service';

describe('PdfBuilderService', () => {
  let service: PdfBuilderService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PdfBuilderService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
