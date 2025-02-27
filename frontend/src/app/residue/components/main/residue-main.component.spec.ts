import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ResidueMainComponent } from './residue-main.component';
import { ResiduesService } from '../../services/residues.service';
import { DialogServiceService } from 'src/app/shared/services/dialog-service.service';
import { ActivatedRoute, Params, Router } from '@angular/router';
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
  let activatedRoute:ActivatedRoute
  
  beforeEach(async () => {
    
    let mock = new ActivatedRouteMock();
    await TestBed.configureTestingModule({
      declarations: [ ResidueMainComponent ],
      providers:[
        {provide:ActivatedRoute,useValue:mock},
        {provide:ResiduesService,userValue:jasmine.createSpyObj('ResiduesService',['getAll'])},
        {provide:DialogServiceService,useValue:jasmine.createSpyObj('DialogService',['openProgressDialog', 'openErrorDialog'])},
        
      ],
      imports:[FormsModule,SharedModule,HttpClientModule]
    })
    .compileComponents();

    //injetando dependencias
    fixture = TestBed.createComponent(ResidueMainComponent);
    residueService = TestBed.inject(ResiduesService) as jasmine.SpyObj<ResiduesService>;
    dialogService = TestBed.inject(DialogServiceService) as jasmine.SpyObj<DialogServiceService>;
    activatedRoute = TestBed.inject(ActivatedRoute);
    component = fixture.componentInstance;
    fixture.detectChanges();


    //setando retorno dos metodos
    
  });

  it('should create', () => {  
    expect(component).toBeTruthy();
  });


});
