
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

// Other imports
import { TestBed } from '@angular/core/testing';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { ResiduesService } from './residues.service';
import { Residue } from 'src/app/shared/entities/Residue';
import { environment } from 'src/environments/environment';

fdescribe('ResidueService', () => {
  let service: ResiduesService;
  let httpClient: HttpClient;
  let httpTestingController: HttpTestingController;
  //test
  let mockResidue:Residue;


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
    mockResidue = {id:1,type:'teste',description:'teste'}
  });

  afterEach(() => {
    httpTestingController.verify();
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
  });

  it('should the refreshRequired subjet to send the list', () =>{
    spyOn(service['refreshRequired'],'next');
    service.sendNull();
    expect(service['refreshRequired'].next).toHaveBeenCalledWith(service.list);
  });

  it('shouled test the save method successfully', () => {
    let savedResidue:Residue = {id:1, type:'teste',description:'teste'}; 
    service.save(mockResidue).subscribe(response => {
      expect(response).toEqual(savedResidue);
    });

    const req = httpTestingController.expectOne(environment.LOCAL_API_URL + 'residues');
    expect(req.request.method).toEqual('POST');
    req.flush(savedResidue);
  })
});
