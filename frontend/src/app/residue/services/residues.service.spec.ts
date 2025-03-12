
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

// Other imports
import { TestBed } from '@angular/core/testing';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { ResiduesService } from './residues.service';

fdescribe('ResidueService', () => {
  let service: ResiduesService;
  let httpClient: HttpClient;
  let httpTestingController: HttpTestingController;


  beforeEach(() => {
    
    TestBed.configureTestingModule({
        providers:[
          ResiduesService,
        ],
        imports:[
          HttpClientTestingModule
        ]
    })

    
    service = TestBed.inject(ResiduesService);
    httpClient = TestBed.inject(HttpClient);
    httpTestingController = TestBed.inject(HttpTestingController);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should get refreshRequired as observable', () => {
    spyOn(service['refreshRequired'],'asObservable');
    service.refreshAllData();
    expect(service['refreshRequired'].asObservable).toHaveBeenCalled();
  });

  it('should the refreshRequired subjet to send the data', () =>{
    let obj = {id:'teste'}
    spyOn(service['refreshRequired'],'next');
    service.send(obj);
    expect(service['refreshRequired'].next).toHaveBeenCalledWith(obj);
  })
});
