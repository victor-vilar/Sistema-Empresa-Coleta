import { TestBed } from '@angular/core/testing';

import { ResidueDetailComponent } from './residue-detail.component';
import { Residue } from 'src/app/shared/entities/Residue';
import { HttpClientModule } from '@angular/common/http';
import { ResidueModule } from '../../modules/residue.module';
import { MatDialogRef } from '@angular/material/dialog';

describe('ResidueDetailComponent', () => {
    let component: ResidueDetailComponent;
  
    beforeEach(() => {
      TestBed.configureTestingModule(
        {
            imports:[HttpClientModule,ResidueModule]
        }
        );
      component = TestBed.inject(ResidueDetailComponent);
      
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