
import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ResiduesService } from './residues.service';
import { HttpClient } from '@angular/common/http';

describe('ResidueServiceTest', () => {
  let service: ResiduesService;

  beforeEach(() => {
    const setup = (httClient:unknown) => {
      TestBed.configureTestingModule({providers:[
        {
          provide:HttpClient,
          useValue:httClient
        }
      ]}).inject(ResiduesService)
    }
    

    service = TestBed.inject(ResiduesService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
