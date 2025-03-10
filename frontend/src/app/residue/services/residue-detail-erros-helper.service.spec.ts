import { TestBed } from '@angular/core/testing';

import { ResidueDetailErrosHelperService } from './residue-detail-erros-helper.service';
import { DialogServiceService } from 'src/app/shared/services/dialog-service.service';
import { FormControl, FormGroup, NgForm, Validators } from '@angular/forms';

fdescribe('ResidueDetailErrosHelperService', () => {
  let service: ResidueDetailErrosHelperService;
  let dialogService:jasmine.SpyObj<DialogServiceService>;
  let invalidType = false;
  let inavlidDescription = false;
  let form;
  

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers:[
        ResidueDetailErrosHelperService,
        {provide:DialogServiceService,useValue:jasmine.createSpyObj('DialogService',['openProgressDialog', 'closeProgressSpinnerDialog','openErrorDialog','openSucessDialog'])},
      ]
    });
    service = TestBed.inject(ResidueDetailErrosHelperService);
    dialogService = TestBed.inject(DialogServiceService) as jasmine.SpyObj<DialogServiceService>
    
    form = new FormGroup({
      type: new FormControl(null),
      description: new FormControl(null)
    });

  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should call the checkIfInputFieldsAreFilled',() => {
    spyOn(service,'checkIfInputFieldsAreFilled');
    service.checkErrors(form,invalidType,inavlidDescription);
    expect(service.checkIfInputFieldsAreFilled).toHaveBeenCalled();
  })
  


});
