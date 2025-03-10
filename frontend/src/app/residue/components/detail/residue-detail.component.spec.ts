import { ComponentFixture, fakeAsync, flush, TestBed, tick } from '@angular/core/testing';

import { ResidueDetailComponent } from './residue-detail.component';
import { Residue } from 'src/app/shared/entities/Residue';
import { HttpClientModule } from '@angular/common/http';
import { ResidueModule } from '../../modules/residue.module';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { DialogServiceService } from 'src/app/shared/services/dialog-service.service';
import { ActivatedRouteMock } from 'src/app/tests/activated-route-mock';
import { ActivatedRoute, Router } from '@angular/router';
import { ResiduesService } from '../../services/residues.service';
import { ErrorsHelperService } from 'src/app/shared/services/erros-helper.service';
import { ErrorStateMatcher } from '@angular/material/core';
import { By } from '@angular/platform-browser';
import { of } from 'rxjs';

fdescribe('ResidueDetailComponent', () => {
    let component: ResidueDetailComponent;
    let fixture: ComponentFixture<ResidueDetailComponent>;
    
    //dependencies
    let activatedRoute:ActivatedRouteMock;
    let router:jasmine.SpyObj<Router>;
    let dialogService:jasmine.SpyObj<DialogServiceService>;
    let residueService:jasmine.SpyObj<ResiduesService>;
    let errorHelper:jasmine.SpyObj<ErrorsHelperService>;

    //test
    let mockResidue:Residue;
  
    beforeEach(() => {
      
      activatedRoute = new ActivatedRouteMock();

      TestBed.configureTestingModule(
        {
            imports:[ResidueModule],
            declarations:[ResidueDetailComponent],
            providers:[
              {provide:ActivatedRoute,useValue:activatedRoute},       
              {provide:Router,useValue:jasmine.createSpyObj('Router',[''])},
              {provide:DialogServiceService,useValue:jasmine.createSpyObj('DialogService',['openProgressDialog', 'closeProgressSpinnerDialog','openErrorDialog','openSucessDialog'])},
              {provide:ResiduesService,useValue:jasmine.createSpyObj('ResiduesService',['getAll','save','update'])},
              {provide:ErrorsHelperService,useValue:jasmine.createSpyObj('ErrosHelperService',[''])},
              {provide: MatDialogRef,useValue: jasmine.createSpyObj('dialogRef',['close'])},
              {provide: MAT_DIALOG_DATA,useValue: {}}
            ]
        }
        );

    //injetando dependencias
    fixture = TestBed.createComponent(ResidueDetailComponent);
    router = TestBed.inject(Router) as jasmine.SpyObj<Router>;
    dialogService = TestBed.inject(DialogServiceService) as jasmine.SpyObj<DialogServiceService>;
    residueService = TestBed.inject(ResiduesService) as jasmine.SpyObj<ResiduesService>;
    errorHelper = TestBed.inject(ErrorsHelperService) as jasmine.SpyObj<ErrorsHelperService>;
    component = fixture.componentInstance;
    fixture.detectChanges();

    //test
    mockResidue = {id:1,type:'teste',description:'teste'}
      
    //mock method
    residueService.save.and.returnValue(of(mockResidue));
    residueService.update.and.returnValue(of(mockResidue));

    });
  
    it('should be created', () => {
      expect(component).toBeTruthy();
    });

    it('should create a new Residue instance when call createObject', () => {
        component.form.value.type='infectante';
        component.form.value.description='descricao';
        let residue:Residue = component.createObject();
        expect(residue.id).toBe(undefined);
        expect(residue.type).toBe('infectante');
        expect(residue.description).toBe('descricao');
    });

    it('should create add the id of a residue when idOfEditedItem is not undefined', () => {
      component['idOfEditedItem'] = 1;
      component.form.value.type='infectante';
      component.form.value.description='descricao';
      let residue:Residue = component.createObject();
      expect(residue.id).toBe(1);
      expect(residue.type).toBe('infectante');
      expect(residue.description).toBe('descricao');
    });

    it('should set clientCpfCnpj variable to undefined when MAT_DIALOG_DATA has no clientCpfCnpj on OnLoad Method',() =>{
      component.ngOnInit();
      expect(component['clientCpfCnpj']).toBeUndefined();
    })

    it('should set clietCpfCnpj variable when MAT_DIALOG_DATA has has clientCpfCnpj on OnLoad Method',() =>{
      component.data = {clientCpfCnpj:'1'};
      component.ngOnInit();
      expect(component['clientCpfCnpj']).toBe('1');
    })

    it('should set objectToEdit to undefined when MAT_DIALOG_ATA has no objectToEdit on OnLoad Method',() => {
      component.ngOnInit();
      expect(component['objectToEdit']).toBeUndefined();  
    });

    it('should set objectToEdit to undefined when MAT_DIALOG_ATA has an objectToEdit on OnLoad Method',() => {
      component.data = {objectToEdit:{id:1,name:'object'}};
      component.ngOnInit();
      expect(component['objectToEdit']).not.toBeUndefined();
      expect(component['objectToEdit'].id).toBe(1);
      expect(component['objectToEdit'].name).toBe('object');
    });

    it('should set crudToOperation to "Atualização" when MAT_DIALOG_ATA has an objectToEdit on OnLoad Method',() => {
      component.data = {objectToEdit:{id:1,name:'object'}};
      component.ngOnInit();
      expect(component['crudOperation']).toBe('Atualização');
    });

    it('should get the cpfCnpj when an object to edit it is a Customer type and add to idOfEditedItem on OnLoad Method', () => {
      component.data = {objectToEdit:{cpfCnpj:111,name:'object'}};
      component.ngOnInit();
      expect(component['idOfEditedItem']).toBe(111);
    })

    it('should get the id when an object to edit it is not of a Customer type and add the id to idOfEditedItem on OnLoad Method', () => {
      component.data = {objectToEdit:{id:111,name:'object'}};
      component.ngOnInit();
      expect(component['idOfEditedItem']).toBe(111);
    });

    it('should fill the form fields if there is an object to edit after the view init',(done:DoneFn) =>{
      component['objectToEdit']={type:'Infectante',description:'Resíduo Infectante'};
      component.ngAfterViewInit();
      fixture.detectChanges();
      
      setTimeout(() => {
        let inputs = fixture.debugElement.queryAll(By.css('input'));
        expect(inputs[0].nativeElement.value).toBe(component['objectToEdit'].type);
        expect(inputs[1].nativeElement.value).toBe(component['objectToEdit'].description);
        expect(component.form.value.type).toBe(component['objectToEdit'].type);
        expect(component.form.value.description).toBe(component['objectToEdit'].description);
        done();
      },200);

    });

    it('should call save method when submit form button',() =>{
      spyOn(component,'save');
      let buttons = fixture.debugElement.queryAll(By.css('button'));
      buttons[0].nativeElement.click();
      expect(component.save).toHaveBeenCalled();
    });

    it('should call cleanForm method when click in the view',() =>{
      spyOn(component,'cleanForm');
      let buttons = fixture.debugElement.queryAll(By.css('button'));
      buttons[1].nativeElement.click();
      expect(component.cleanForm).toHaveBeenCalled();
    });

    it('should test resetInvalidProperties correctly',() => {
      component.isInvalidType = true;
      component.isInvalidDescription = true;
      component.resetInvalidProperties();
      expect(component.isInvalidType).toBeFalse();
      expect(component.isInvalidDescription).toBeFalse();
    })

    it('save method should call resetProperties, checkErrors from ErrorHelper and openProgressSpinner from DialogService and createObject',() => {
      spyOn(component,'resetInvalidProperties');
      spyOn(component['errorHelper'],'checkErrors');
      spyOn(component,'createObject').and.callThrough();
      component.form.value.type='infectante';
      component.form.value.description='descricao';

      component.save();

      expect(component.resetInvalidProperties).toHaveBeenCalled();
      expect(component['errorHelper'].checkErrors).toHaveBeenCalled();
      expect(dialogService.openProgressDialog).toHaveBeenCalled();
      expect(component.createObject).toHaveBeenCalled();
    });

    it('should save and new Residue succesfully',(fakeAsync(() =>{
      spyOn(component,'destroy');
      component.form.value.type='infectante';
      component.form.value.description='descricao';
      component.save();
      tick(1000);
      expect(residueService.save).toHaveBeenCalledTimes(1);
      expect(dialogService.openSucessDialog).toHaveBeenCalled();
      expect(residueService.getAll).toHaveBeenCalledTimes(1);
      expect(component['destroy']).toHaveBeenCalled();
      expect(dialogService.closeProgressSpinnerDialog).toHaveBeenCalled();
    })));

    it('should call service update method when there is an idOfEditedItem',(fakeAsync(() =>{
      spyOn(component,'destroy');
      component['idOfEditedItem'] = 1;
      component.form.value.type='infectante';
      component.form.value.description='descricao';
      component.save();
      tick(1000);
      expect(residueService.update).toHaveBeenCalledTimes(1);
      expect(dialogService.openSucessDialog).toHaveBeenCalled();
      expect(residueService.getAll).toHaveBeenCalledTimes(1);
      expect(component['destroy']).toHaveBeenCalled();
      expect(dialogService.closeProgressSpinnerDialog).toHaveBeenCalled();
    })));

    it('should test cleanForm successfully',() => {
      spyOn(component.form,'reset');
      spyOn(component,'resetInvalidProperties');
      component.cleanForm();
      expect(component.form.reset).toHaveBeenCalled();
      expect(component.resetInvalidProperties).toHaveBeenCalled();
    });

    it('should test the destroy method successfully',() => {
      spyOn(component['destroy$'],'next');
      spyOn(component['destroy$'],'complete');
      component.destroy();
      expect(component['destroy$'].next).toHaveBeenCalled();
      expect(component['destroy$'].complete).toHaveBeenCalled();
      expect(component.dialogRef.close).toHaveBeenCalled();
    })




    





  });