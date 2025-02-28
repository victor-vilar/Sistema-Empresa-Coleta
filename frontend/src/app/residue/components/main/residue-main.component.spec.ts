import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ResidueMainComponent } from './residue-main.component';
import { ResiduesService } from '../../services/residues.service';
import { DialogServiceService } from 'src/app/shared/services/dialog-service.service';
import { ActivatedRoute, convertToParamMap, Params, Router } from '@angular/router';
import { SharedModule } from 'src/app/shared/shared.module';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { Observable, of, Subject } from 'rxjs';
import { ActivatedRouteMock } from 'src/app/tests/activated-route-mock';

fdescribe('ResidueMainComponent', () => {
  let component: ResidueMainComponent;
  let fixture: ComponentFixture<ResidueMainComponent>;
  let residueService:jasmine.SpyObj<ResiduesService>;
  let dialogService:jasmine.SpyObj<DialogServiceService>;
  let activatedRoute:ActivatedRouteMock;
  
  beforeEach(async () => {
    
    activatedRoute = new ActivatedRouteMock();

    await TestBed.configureTestingModule({
      declarations: [ ResidueMainComponent ],
      providers:[
        {provide:ActivatedRoute,useValue:activatedRoute},//{queryParams:of(convertToParamMap({'dialog':true}))}},
        {provide:ResiduesService,userValue:jasmine.createSpyObj('ResiduesService',['getAll'])},
        {provide:DialogServiceService,useValue:jasmine.createSpyObj('DialogService',['openDialog', 'openErrorDialog'])},
        
      ],
      imports:[FormsModule,SharedModule,HttpClientModule]
    })
    .compileComponents();

    //injetando dependencias
    fixture = TestBed.createComponent(ResidueMainComponent);
    residueService = TestBed.inject(ResiduesService) as jasmine.SpyObj<ResiduesService>;
    dialogService = TestBed.inject(DialogServiceService) as jasmine.SpyObj<DialogServiceService>;
    component = fixture.componentInstance;
    fixture.detectChanges();

  });

  it('should create', () => {  
    expect(component).toBeTruthy();
  });

  it('should call the super.onInit and subscribe to queryParams',() => {
    spyOn(component,'openDialog');
    component.ngOnInit();
    activatedRoute.testParams = {dialog:true}
    expect(component.openDialog).toHaveBeenCalled();
  });

  it('should set the path, the title and pathToOperations properties',() => {
    component.ngOnInit();
    expect(component['title']).toBe('Residuos');
    expect(component['path']).toBe('residuo');
    expect(component['pathToOperations'][0].name).toBe('Cadastrar novo Resíduo');
    expect(component['pathToOperations'][0].path).toBe(component['path'] + '/novo');
  })

  it('should add the variable to the objectToEdit propertie', () => {
    let obj = {name:'teste',id:'123456'}
    component.editObject(obj);
    expect(component['objectToEdit'].name).toBe(obj.name);
    expect(component['objectToEdit'].id).toBe(obj.id);
    expect(component['objectToEdit']).toEqual(obj);
  });

  it('should the ngOnDestroy method call the each subscription and unsubscribe',() => {
    
    component['subscriptions'].push(of(true).subscribe(r =>{}));
    component['subscriptions'].push(of(true).subscribe(r =>{}));
    spyOn(component['subscriptions'][0],'unsubscribe');
    spyOn(component['subscriptions'][1],'unsubscribe');
    
    component.ngOnDestroy();
    expect(component['subscriptions'][0].unsubscribe).toHaveBeenCalled();
    expect(component['subscriptions'][1].unsubscribe).toHaveBeenCalled();
    
  })


});
