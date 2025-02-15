import { fakeAsync, TestBed, tick } from '@angular/core/testing';

import { CommunicationService } from './communication.service';

fdescribe('CommunicationService', () => {
  let service: CommunicationService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CommunicationService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should send object to edit',(fakeAsync(() =>{
    
    let response:any = 'antigo';
    let obs = service.dataEmitter;
    
    obs.subscribe(data => {
      console.log('cheguei')  
      response = data;
      
    })



    spyOn(service.dataEmitter,'next').and.callThrough();
    service.sendData('this is an test');
    console.log(response);
    tick(50);
    expect(service.dataEmitter.next).toHaveBeenCalled();
    expect(response).toBe('this is an test');
    


  })))

});
