import { ComponentFixture, TestBed } from '@angular/core/testing';

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

fdescribe('ResidueDetailComponent', () => {
    let component: ResidueDetailComponent;
    let fixture: ComponentFixture<ResidueDetailComponent>;
    
    //dependencies
    let activatedRoute:ActivatedRouteMock;
    let router:jasmine.SpyObj<Router>;
    let dialogService:jasmine.SpyObj<DialogServiceService>;
    let residueService:jasmine.SpyObj<ResiduesService>;
    let errorHelper:jasmine.SpyObj<ErrorsHelperService>;
  
    beforeEach(() => {
      
      activatedRoute = new ActivatedRouteMock();

      TestBed.configureTestingModule(
        {
            imports:[ResidueModule],
            declarations:[ResidueDetailComponent],
            providers:[
              {provide:ActivatedRoute,useValue:activatedRoute},       
              {provide:Router,useValue:jasmine.createSpyObj('Router',[''])},
              {provide:DialogServiceService,useValue:jasmine.createSpyObj('DialogService',['openDialog', 'openErrorDialog'])},
              {provide:ResiduesService,userValue:jasmine.createSpyObj('ResiduesService',['getAll'])},
              {provide:ErrorsHelperService,useValue:jasmine.createSpyObj('ErrosHelperService',[''])},
              {provide: MatDialogRef,useValue: jasmine.createSpy('close')},
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
    })


  });