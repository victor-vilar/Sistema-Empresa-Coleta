import { TestBed } from '@angular/core/testing';

import { ResidueDetailErrosHelperService } from './residue-detail-erros-helper.service';
import { DialogServiceService } from 'src/app/shared/services/dialog-service.service';
import { FormControl, FormGroup, NgForm, Validators } from '@angular/forms';

class mockComponent {
  isInvalidType:boolean = false;
  isInvalidDescription:boolean = false;

}

describe('ResidueDetailErrosHelperService', () => {
  let service: ResidueDetailErrosHelperService;
  let dialogService:jasmine.SpyObj<DialogServiceService>;
  let form;
  let INVALID_RESIDUE_TYPE_MESSAGE = "O tipo do residuo não pode ser vazio!";
  let INVALID_CLASS_TYPE_MESSAGE = "A classe do residuo não pode ser vazio!";
  

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers:[
        ResidueDetailErrosHelperService,
        {provide:DialogServiceService,useValue:jasmine.createSpyObj('DialogService',['openErrorDialog'])},
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
    let component = new mockComponent();
    spyOn(service,'checkIfInputFieldsAreFilled');
    service.checkErrors(form,component.isInvalidType,component.isInvalidDescription);
    expect(service.checkIfInputFieldsAreFilled).toHaveBeenCalled();
  })

  it('should set invalidType to true, call dialogService and throw error if the form field type it is null', () => {
    form.value.type = '';
    form.value.description ='fake description';
    let component = new mockComponent();
    
    try{
      service.checkErrors(form,component.isInvalidType,component.isInvalidDescription);
    }catch(err){
      expect(dialogService.openErrorDialog).toHaveBeenCalled();
    }

    expect(() => service.checkErrors(form,component.isInvalidType,component.isInvalidDescription)).toThrow(new Error(INVALID_RESIDUE_TYPE_MESSAGE));
    

  })

  it('should set invalidDescription to true, call dialogService and throw error if the form field type it is null', () => {
    form.value.type = 'fake type';
    form.value.description ='';
    let component = new mockComponent();
    
    try{
      service.checkErrors(form,component.isInvalidType,component.isInvalidDescription);
    }catch(err){
      expect(dialogService.openErrorDialog).toHaveBeenCalled();
    }

    expect(() => service.checkErrors(form,component.isInvalidType,component.isInvalidDescription)).toThrow(new Error(INVALID_CLASS_TYPE_MESSAGE));
    

  })



});
